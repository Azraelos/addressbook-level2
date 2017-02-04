package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "a/123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in this format: a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public static final int INDEX_BLOCK = 0;
    public static final int INDEX_STREET = 1;
    public static final int INDEX_UNIT = 2;
    public static final int INDEX_POSTALCODE = 3;
    
    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;
    private boolean isPrivate;
    
    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        String[] addressComponents = trimmedAddress.split(",");
        for(int i=0; i<addressComponents.length; i++){
           	addressComponents[i] = addressComponents[i].trim();
        }
        this.block = new Block(addressComponents[INDEX_BLOCK]);
        this.street = new Street(addressComponents[INDEX_STREET]);               
        this.unit = new Unit(addressComponents[INDEX_UNIT]);
        this.postalCode = new  PostalCode(addressComponents[INDEX_POSTALCODE]);
                
        this.isPrivate = isPrivate;
        //if (!isValidAddress(trimmedAddress)) {
        //    throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        //}
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return block.getBlock() + ", " + street.getStreet() + ", " + unit.getUnit() + ", " + postalCode.getPostalCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
