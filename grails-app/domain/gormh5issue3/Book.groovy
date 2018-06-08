package gormh5issue3

class Book {
	
	String title
	Author author
	boolean isScientific

    static constraints = {
    }
	
	static whereScientificOnly = where {
		isScientific == true
	}

	static namedQueries = {
		namedQueryScientificOnly {
			eq('isScientific', true)
		}
	}

	String toString() {
		return this.title
	}
}
