package gormh5issue3

class BootStrap {

    def init = { servletContext ->
        // 3 authors
        Author author1 = new Author()
        author1.firstName = "John"
        author1.lastName = "Doe"
        author1.username = "a1"
        author1.save(flush:true)

        Author author2 = new Author()
        author2.firstName = "Hans"
        author2.lastName = "Wurst"
        author2.username = "a2"
        author2.save(flush:true)

        Author author3 = new Author()
        author3.firstName = "Max"
        author3.lastName = "Muster"
        author3.username = "a3"
        author3.save(flush:true)


        // 10 books

        // 6 non-scientific
        Book book1 = new Book()
        book1.title = "Adventures of a cat"
        book1.author = author1
        book1.isScientific = false
        book1.save(flush:true)

        Book book2 = new Book()
        book2.title = "A nice love story"
        book2.author = author1
        book2.isScientific = false
        book2.save(flush:true)

        Book book3 = new Book()
        book3.title = "Collection of boring criminal cases"
        book3.author = author2
        book3.isScientific = false
        book3.save(flush:true)

        Book book4 = new Book()
        book4.title = "How to cook good soup"
        book4.author = author2
        book4.isScientific = false
        book4.save(flush:true)

        Book book5 = new Book()
        book5.title = "My first year in Canada"
        book5.author = author3
        book5.isScientific = false
        book5.save(flush:true)

        Book book6 = new Book()
        book6.title = "A story about knights and dragons"
        book6.author = author3
        book6.isScientific = false
        book6.save(flush:true)



        // 4 scientific
        Book book7 = new Book()
        book7.title = "Linguistics"
        book7.author = author1
        book7.isScientific = true
        book7.save(flush:true)

        Book book8 = new Book()
        book8.title = "Mathematics"
        book8.author = author2
        book8.isScientific = true
        book8.save(flush:true)

        Book book9 = new Book()
        book9.title = "Chemistry"
        book9.author = author3
        book9.isScientific = true
        book9.save(flush:true)

        Book book10 = new Book()
        book10.title = "Biology"
        book10.author = author3
        book10.isScientific = true
        book10.save(flush:true)
    }
    def destroy = {
    }
}
