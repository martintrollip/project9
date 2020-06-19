package martintrollip.task2;

/**
 * Personal details. Use the builder to construct a new instance.
 *
 * @author Martin Trollip
 * @since 2020/06/18 19:06
 */
public final class PersonalDetails {

    private final String name;
    private final String surname;
    private final String cellphone;
    private final String address;
    private final String currentRole;

    private PersonalDetails(String name, String surname, String cellphone, String address, String currentRole) {
        this.name = name;
        this.surname = surname;
        this.cellphone = cellphone;
        this.address = address;
        this.currentRole = currentRole;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getAddress() {
        return address;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    @Override
    public String toString() {
        return "PersonalDetails{" + "name='" + name + '\'' + ", surname='" + surname + '\'' + ", cellphone='" + cellphone + '\'' +
                ", address='" + address + '\'' + ", currentRole='" + currentRole + '\'' + '}';
    }

    public static class PersonalDetailsBuilder {
        private String name;
        private String surname;
        private String cellphone;
        private String address;
        private String currentRole;

        public PersonalDetailsBuilder() {
        }

        public PersonalDetailsBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PersonalDetailsBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonalDetailsBuilder cellphone(String cellphone) {
            this.cellphone = cellphone;
            return this;
        }

        public PersonalDetailsBuilder address(String address) {
            this.address = address;
            return this;
        }

        public PersonalDetailsBuilder currentRole(String currentRole) {
            this.currentRole = currentRole;
            return this;
        }

        public PersonalDetails build() {
            return new PersonalDetails(name, surname, cellphone, address, currentRole);
        }
    }
}
