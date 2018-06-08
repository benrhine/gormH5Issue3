package gormh5issue3

class Author {
	
	String firstName
	String lastName
	String username

    static constraints = {
    }
	
	String toString() {
		return this.username
	}
}
