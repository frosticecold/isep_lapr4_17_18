package pt.isep.nsheets.shared.lapr4.red.s2.core.n1161018.DTO;

import pt.isep.nsheets.shared.core.Address;

import java.util.Objects;

/**
 * CellDTO.java
 *
 * A Cell data sack.
 *
 * @autor: David Blanquett<1161018@isep.ipp.pt>
 * @date: 10/06/2018
 */
public class CellDTO {

    /**
     * The address of the cell
     */
    public Address address; // @Mangalho: Tira isto e mete um DTO se der erro... Mas acho que não

    /**
     * The value of the cell
     */
    public String value;

    public CellDTO(Address address, String value) {
        this.address = address;
        this.value = value;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CellDTO)) return false;
        CellDTO cellDTO = (CellDTO) o;
        return Objects.equals(getAddress(), cellDTO.getAddress()) &&
                Objects.equals(getValue(), cellDTO.getValue());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAddress(), getValue());
    }

    @Override
    public String toString() {
        return "CellDTO{" +
                "address=" + address +
                ", value='" + value + '\'' +
                '}';
    }
}
