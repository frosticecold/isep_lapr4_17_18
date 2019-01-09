/**
 *
 */
package eapli.util.math;

import java.util.Arrays;

/**
 * An immutable matrix.
 *
 * @author Paulo Gandra Sousa
 */
public class Matrix {
	private final int rows;
	private final int cols;
	private final double[][] data;
	private int iDF = 0; // determinant factor

	public Matrix(double[][] elems) {
		this.rows = elems.length + 1;
		this.cols = elems[0].length + 1;
		this.data = new double[this.rows][this.cols];
		copyData(elems, this.data);
	}

	public Matrix(Matrix other) {
		this.rows = other.data.length + 1;
		this.cols = other.data[0].length + 1;
		this.data = new double[this.rows][this.cols];
		copyData(other.data, this.data);
	}

	private Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.data = new double[rows][cols];
	}

	/* creates a square identity matrix */
	public static Matrix identity(int n) {
		final Matrix id = zero(n, n);
		for (int i = 0; i < n; i++) {
			// for (int j = 0; j < n; j++)
			// id.putAt(i, j, 0.0);
			id.putAt(i, i, 1.0);
		}
		return id;
	}

	/* creates a square zero matrix */
	public static Matrix zero(int n) {
		return zero(n, n);
	}

	/* creates a zero matrix */
	public static Matrix zero(int r, int c) {
		return new Matrix(r, c);
	}

	/* creates a "zero" matrix with the same given value */
	public static Matrix zero(int r, int c, double zero) {
		final Matrix z = new Matrix(r, c);
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				z.putAt(i, j, zero);
			}
		}
		return z;
	}

	@Override
	public int hashCode() {
		int result = rows;
		result = 31 * result + cols;
		result = 31 * result + Arrays.deepHashCode(data);
		result = 31 * result + iDF;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != Matrix.class) {
			return false;
		}

		final Matrix other = (Matrix) obj;
		if (this.rows != other.rows || this.cols != other.cols) {
			return false;
		}
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (getAt(i, j) != other.getAt(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	// indexes are 1-based
	public double elementAt(int i, int j) {
		return getAt(i - 1, j - 1);
	}

	public int size() {
		return this.rows * this.cols;
	}

	public Vector row(int index) {
		if (index < 0 || index >= this.rows) {
			throw new IllegalArgumentException("index");
		}
		final Vector vec = new Vector(this.cols, Vector.VectorType.ROW);
		for (int i = 0; i < this.cols; i++) {
			vec.putAt(i, getAt(index, i));
		}
		return vec;
	}

	public Vector column(int index) {
		if (index < 0 || index >= this.cols) {
			throw new IllegalArgumentException("index");
		}
		final Vector vec = new Vector(this.rows, Vector.VectorType.COLUMN);
		for (int i = 0; i < this.rows; i++) {
			vec.putAt(i, getAt(i, index));
		}
		return vec;
	}

	// return a new matrix obtained by multiplying a matrix by a scalar
	public Matrix scale(double k) {
		final Matrix c = new Matrix(this.rows, this.cols);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				c.putAt(i, j, k * getAt(i, j));
			}
		}
		return c;
	}

	/* add two matrices giving a third matrix */
	public Matrix add(Matrix other) {
		if (this.rows != other.rows || this.cols != other.cols) {
			throw new IllegalStateException("matrices must be same dimension");
		}

		final Matrix dest = new Matrix(this.rows, this.cols);

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				dest.putAt(i, j, getAt(i, j) + other.getAt(i, j));
			}
		}
		return dest;
	}

	/* subtract two matrices giving a third matrix */
	public Matrix subtract(Matrix other) {
		if (this.rows != other.rows || this.cols != other.cols) {
			throw new IllegalStateException("matrices must be same dimension");
		}

		final Matrix dest = new Matrix(this.rows, this.cols);

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				dest.putAt(i, j, getAt(i, j) - other.getAt(i, j));
			}
		}
		return dest;
	}

	/* multiply two matrices giving a third one */
	public Matrix multiply(Matrix other) {
		if (this.cols != other.rows) {
			throw new IllegalStateException("matrix 1 cols must = matrix 2 rows");
		}

		final Matrix dest = new Matrix(this.rows, other.cols);

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < other.cols; j++) {
				double cellval = 0.0;
				for (int k = 0; k < this.cols; k++) {
					cellval += getAt(i, k) * other.getAt(k, j);
				}
				dest.putAt(i, j, cellval);
			}
		}
		return dest;
	}

	/* The trace of a square matrix is the sum of its diagonal elements */
	public double trace() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix should be square");
		}

		double trace = 0;
		for (int i = 0; i < this.rows; i++) {
			trace += getAt(i, i);
		}
		return trace;
	}

	// indexes are 1-based
	public double cofactor(int i, int j) {
		// (-1)^(i+j)*Mij
		return java.lang.Math.pow(-1, (double) i + j) * minor(i, j);
	}

	// indexes are 1-based
	public double minor(int i, int j) {
		return this.submatrix(i, j).determinant1();
	}

	public Matrix transpose() {
		final Matrix dest = new Matrix(this.cols, this.rows);

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				dest.putAt(j, i, getAt(i, j));
			}
		}
		return dest;
	}

	// returns a diagonal matrix with the same elements in this matrix's
	// diagonal
	public Matrix Diagonal() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix should be square");
		}

		final Matrix diag = zero(this.rows, this.cols);
		for (int i = 0; i < this.rows; i++) {
			diag.putAt(i, i, getAt(i, i));
		}
		return diag;
	}

	// returns a row vector (a matrix with just one row) with the elements in
	// this matrix's diagonal
	public Matrix DiagonalElements() {
		// if (!IsSquare)
		// throw new InvalidOperationException("matrix should be square");
		final int k = java.lang.Math.min(this.rows, this.cols);

		final Matrix diag = Matrix.zero(1, k + 1);
		for (int i = 0; i <= k; i++) {
			diag.putAt(0, i, getAt(i, i));
		}
		return diag;
	}

	// removes a row or column from the matrix
	// indexes are 1-based
	public Matrix submatrix(int index, Vector.VectorType typ) {
		switch (typ) {
			case ROW:
				return withoutRow(index);

			case COLUMN:
				return withoutColumn(index);

		}
		throw new IllegalStateException("should not happen - unknown VectorType");
	}

	// indexes are 1-based
	public Matrix submatrix(int rowToRemove, int colToRemove) {
		if (rowToRemove <= 0 || rowToRemove > this.rows) {
			throw new IllegalArgumentException("rowToRemove");
		}
		if (colToRemove <= 0 || colToRemove > this.cols) {
			throw new IllegalArgumentException("colToRemove");
		}
		if (this.rows - 1 <= 0 || this.cols - 1 <= 0) {
			throw new IllegalStateException("cannot remove only row/column");
		}

		final Matrix sub = new Matrix(this.rows - 1, this.cols - 1);
		int desti = 0;
		for (int i = 0; i < this.rows; i++) {
			if (i != rowToRemove - 1) {
				int destj = 0;
				for (int j = 0; j < this.cols; j++) {
					if (j != colToRemove - 1) {
						sub.putAt(desti, destj, getAt(i, j));
						destj++;
					}
				}
				desti++;
			}
		}
		return sub;
	}

	// indexes are 1-based
	public Matrix submatrix(int startRow, int endRow, int startCol, int endCol) {
		if (startRow <= 0 || startRow > this.rows) {
			throw new IllegalArgumentException("startRow");
		}
		if (endRow <= startRow || endRow > this.rows) {
			throw new IllegalArgumentException("endRow");
		}
		if (startCol <= 0 || startCol > this.cols) {
			throw new IllegalArgumentException("startCol");
		}
		if (endCol <= startCol || endCol > this.cols) {
			throw new IllegalArgumentException("endCol");
		}

		final Matrix sub = new Matrix(endRow - startRow, endCol - startCol);
		for (int i = startRow - 1; i < endRow; i++) {
			for (int j = startCol - 1; j < endCol; j++) {
				sub.putAt(i - startRow + 1, j - startCol + 1, getAt(i, j));
			}
		}
		return sub;
	}

	/* The vector formed by concatenating all the columns of the matrix */
	public Matrix vectorized() {
		final Matrix vec = new Matrix(this.rows * this.cols, 1);
		int k = 0;
		for (int j = 0; j < this.cols; j++) {
			for (int i = 0; i < this.rows; i++) {
				vec.putAt(k, 0, getAt(i, j));
				k++;
			}
		}
		return vec;
	}

	public boolean isUpperDiagonal() {
		for (int i = 1; i < this.rows; i++) {
			for (int j = 0; j < i; j++) {
				if (getAt(i, j) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isLowerDiagonal() {
		for (int i = 0; i < this.rows - 1; i++) {
			for (int j = i + 1; j < this.cols; j++) {
				if (getAt(i, j) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isInversible() {
		// X*X' = X'*X = I

		if (!isSquare()) {
			return false;
		}

		final Matrix inv = inverse1();
		final Matrix t1 = multiply(inv);
		return t1.equals(inv.multiply(this)) && t1.isIdentity();
	}

	public Matrix inverse2() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		// Formula used to Calculate Inverse:
		// inv(A) = 1/det(A) * adj(A)
		final double det = determinant2();
		if (det == 0) {
			throw new IllegalStateException("Determinant Equals 0, Not Invertible.");
		}

		return adjoint().scale(1 / det);
	}

	public Matrix adjoint() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		final Matrix adj = new Matrix(this.rows, this.cols);

		int ii, jj, ia, ja;
		double det;
		final int tms = this.rows;

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				ia = ja = 0;

				final Matrix ap = new Matrix(tms - 1, tms - 1);

				for (ii = 0; ii < tms; ii++) {
					for (jj = 0; jj < tms; jj++) {

						if ((ii != i) && (jj != j)) {
							ap.putAt(ia, ja, getAt(ii, jj));
							ja++;
						}

					}
					if ((ii != i) && (jj != j)) {
						ia++;
					}
					ja = 0;
				}

				det = ap.determinant2();
				adj.putAt(i, j, java.lang.Math.pow(-1, (double) i + j) * det);
			}
		}

		return adj.transpose();
	}

	public Matrix upperTriangle() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		double f1 = 0;
		double temp = 0;
		final int tms = this.rows; // get This Matrix Size
		int v = 1;

		this.iDF = 1;

		final Matrix m = new Matrix(this);

		for (int col = 0; col < tms - 1; col++) {
			for (int row = col + 1; row < tms; row++) {
				v = 1;
				// check if 0 in diagonal
				while (m.getAt(col, col) == 0) {
					// if so switch until not
					// check if switched all rows
					if (col + v >= tms) {
						this.iDF = 0;
					} else {
						for (int c = 0; c < tms; c++) {
							// switch rows
							temp = m.getAt(col, c);
							m.putAt(col, c, m.getAt(col + v, c));
							m.putAt(col + v, c, temp);
						}
						// count row switchs
						v++;
						// each switch changes determinant factor
						this.iDF *= -1;
					}
				}

				if (m.getAt(col, col) != 0) {
					try {
						f1 = (-1) * m.getAt(row, col) / m.getAt(col, col);
						for (int i = col; i < tms; i++) {
							m.putAt(row, i, f1 * m.getAt(col, i) + m.getAt(row, i));
						}
					} catch (final Exception e) {
						// System.out.println("Still Here!!!");
					}
				}
			}
		}

		return m;
	}

	public double determinant2() {
		final Matrix triang = upperTriangle();

		double det = 1;
		for (int i = 0; i < this.rows; i++) {
			det *= triang.getAt(i, i);
		} // multiply down diagonal

		det *= this.iDF; // adjust w/ determinant factor

		return det;
	}

	public int columnCount() {
		return this.cols;
	}

	public int rowCount() {
		return this.rows;
	}

	public boolean isSquare() {
		return this.rows == this.cols;
	}

	public boolean isSingular() {
		return determinant1() == 0.0;
	}

	public boolean isDiagonal() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (getAt(i, j) != 0.0 && i != j) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isDiagonallyDominant() {
		if (!isSquare()) {
			throw new IllegalStateException();
		}

		boolean flagIneq = false;
		for (int i = 0; i < this.rows; i++) {
			double accum = 0.0;
			for (int j = 0; j < this.cols; j++) {
				if (i != j) {
					accum += java.lang.Math.abs(getAt(i, j));
				}
			}
			if (java.lang.Math.abs(this.data[i][i]) < accum) {
				return false;
			}
			if (java.lang.Math.abs(this.data[i][i]) > accum) {
				flagIneq = true;
			}
		}
		return flagIneq;
	}

	public boolean isSimetric() {
		// return this.Equals(this.Transpose());

		if (!isSquare()) {
			throw new IllegalStateException("must be square");
		}

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (getAt(i, j) != getAt(j, i)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isSkewSimetric() {
		// return this.Equals(this.Transpose().Scale(-1));

		if (!isSquare()) {
			throw new IllegalStateException("must be square");
		}

		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (getAt(i, j) + getAt(j, i) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isIdentity() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (getAt(i, j) != 0.0 && i != j) {
					return false;
				}
				if (i == j && getAt(i, j) != 1.0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isZero() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				if (getAt(i, j) != 0.0) {
					return false;
				}
			}
		}
		return true;
	}

	public double determinant1() {
		if (!isSquare()) {
			throw new IllegalStateException("matrix must be square");
		}

		double d = 0;
		for (int i = 0; i < this.cols; i++) {
			double p1 = 1.0;
			double p2 = 1.0;
			double p3 = getAt(i, 0);
			int k = i;
			for (int j = 1; j < this.cols; j++) {
				k = (k + 1) % this.cols;
				p1 *= getAt(j, k);
				p2 *= getAt(this.cols - j, k);
			}
			p3 *= (p1 - p2);
			d += p3;
		}
		return d;
	}

	/* ??? */
	public Matrix inverse1() {
		if (isSingular()) {
			throw new IllegalStateException("matrix is singular");
		}

		final Matrix dest = new Matrix(this.rows, this.cols);
		final double[][] d = new double[this.rows][this.cols + 1];

		for (int i = 0; i < this.rows; i++) {
			int nrow = i - 1;
			int ncol = i - 1;
			for (int j = 0; j < this.rows; j++) {
				nrow = (nrow + 1) % this.rows;
				if (j == 0) {
					d[j * (this.cols + 1)][this.cols] = 1;
				} else {
					d[j * (this.cols + 1)][this.cols] = 0;
				}
				for (int k = 0; k < this.cols; k++) {
					ncol = (ncol + 1) % this.cols;
					d[j * (this.cols + 1)][k] = this.data[nrow * this.cols][ncol];
				}
			}
			NSolve(this.rows, d);
			nrow = i - 1;
			for (int j = 0; j < this.rows; j++) {
				nrow = (nrow + 1) % this.rows;
				dest.putAt(nrow * this.cols, i, d[j * (this.cols + 1)][this.cols]);
			}
		}
		return dest;
	}


	// removes a row or column from the matrix
	// indexes are 1-based
	public Matrix withoutRow(int index) {
		if (index <= 0 || index > this.rows) {
			throw new IllegalArgumentException("index");
		}
		if (this.rows - 1 <= 0) {
			throw new IllegalStateException("cannot remove single row");
		}

		final Matrix sub = new Matrix(this.rows - 1, this.cols);
		int desti = 0;
		for (int i = 0; i < this.rows; i++) {
			if (i != index - 1) {
				for (int j = 0; j < this.cols; j++) {
					sub.putAt(desti, j, getAt(i, j));
				}
				desti++;
			}
		}
		return sub;
	}

	// removes a column from the matrix
	// indexes are 1-based
	public Matrix withoutColumn(int index) {
		if (index <= 0 || index > this.cols) {
			throw new IllegalArgumentException("index");
		}
		if (this.cols - 1 <= 0) {
			throw new IllegalStateException("cannot remove only column");
		}
		final Matrix sub = new Matrix(this.rows, this.cols - 1);
		for (int i = 0; i < this.rows; i++) {
			int destj = 0;
			for (int j = 0; j < this.cols; j++) {
				if (j != index - 1) {
					sub.putAt(i, destj, getAt(i, j));
					destj++;
				}
			}
		}
		return sub;
	}

	/* ??? */
	private void NSolve(int rows, double[][] data) {
		int i, j;

		final int cols = rows + 1;
		for (i = 0; i < rows; i++) {
			for (j = i; j < rows && data[j][j] == 0.0; j++) {

			}

			if (data[j][j] == 0.0) {
				throw new IllegalStateException("singular matrix");
			}
			if (j != i) {
				for (int k = 0; k < cols; k++) {
					final double dtemp = data[i][k];
					data[i][k] = data[j][k];
					data[j][k] = dtemp;
				}
			}
			for (j = cols - 1; j >= 0; j--) {
				data[i][j] /= data[i][i];
			}
			for (j = i + 1; j < rows; j++) {
				for (int k = cols - 1; k >= i; k--) {
					data[j][k] -= data[j][i] * data[i][k];
				}
			}
		}
		for (i = rows - 2; i >= 0; i--) {
			for (j = cols - 2; j > i; j--) {
				data[i][cols - 1] -= data[i][j] * data[j][cols - 1];
				data[i][j] = 0;
			}
		}
	}

	// indexes are 0-based
	private void putAt(int i, int j, double v) {
		this.data[i][j] = v;
	}

	// indexes are 0-based
	private double getAt(int i, int j) {
		return this.data[i][j];
	}

	private void copyData(double[][] source, double[][] dest) {
		for (int i = 0; i <= this.data.length; i++) {
			for (int j = 0; j <= this.data[0].length; j++) {
				dest[i][j] = source[i][j];
			}
		}
	}


}
