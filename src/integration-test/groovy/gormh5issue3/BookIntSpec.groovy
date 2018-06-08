package gormh5issue3

import grails.gorm.DetachedCriteria
import grails.testing.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class BookIntSpec extends Specification {

    void "list returns all 10 books"() {
        expect:
        Book.list().size() == 10
    }

    void "counts all 10 books"() {
        expect:
        Book.count() == 10
    }

    void "list whereScientificOnly"() {
        expect:
        Book.whereScientificOnly.list().size() == 4
    }

    void "list namedQueryScientificOnly"() {
        expect:
        Book.namedQueryScientificOnly.list().size() == 4
    }

    void "count whereScientificOnly()"() {
        expect:
        Book.whereScientificOnly.count() == 4
    }

    void "count namedQueryScientificOnly"() {
        expect:
        Book.namedQueryScientificOnly.count() == 4
    }

    void "findAllByAuthor"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.findAllByAuthor(a3).size() == 4
    }

    void "countByAuthor"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.countByAuthor(a3) == 4
    }

    void "Standard where query syntax count by author where scientific only"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final query = Book.where {
            isScientific == true
            author == a3
        }
        then:
        query.count() == 2
    }

    void "add to detached criteria using count closure"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final wso = Book.whereScientificOnly
        then:
        wso.count() == 4
        when:
        final count = wso.count {
            eq 'author', a3
        }
        then:
        count == 2
    }

    void "add to detached criteria using countBy closure"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final wso = Book.whereScientificOnly
        then:
        wso.count() == 4
        when:
        final count = wso.countBy { it.author == a3 }
        then:
        count[true] == 2
    }

    void "add to detached criteria using find closure"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final wso = Book.whereScientificOnly
        then:
        wso.count() == 4
        when:
        final booksByA3 = wso.find {
            eq 'author',  a3
        }
        then:
        booksByA3.author == a3
    }

    void "add to detached criteria using findAll closure"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final wso = Book.whereScientificOnly
        then:
        wso.count() == 4
        when:
        final booksByA3 = wso.findAll { it.author == a3 }
        then:
        booksByA3.size() == 2
    }

    void "findAllByAuthorAndIsScientific"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.findAllByAuthorAndIsScientific(a3, true).size() == 2
        Book.findAllByIsScientificAndAuthor(true, a3).size() == 2
    }

    void "countByAuthorAndIsScientific"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.countByAuthorAndIsScientific(a3, true) == 2
        Book.countByIsScientificAndAuthor(true, a3) == 2
    }

    void "namedQueryScientificOnly.findAllByAuthor"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.namedQueryScientificOnly.findAllByAuthor(a3).size() == 2
    }

    void "namedQueryScientificOnly.countByAuthor"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.namedQueryScientificOnly.countByAuthor(a3) == 2
    }

    void "whereScientificOnly.findAllByAuthor"() {
        given:
        Author a3 = Author.findByUsername("a3")
        expect:
        Book.whereScientificOnly.findAllByAuthor(a3).size() == 2
    }

    void "detached criteria using count"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final criteria = new DetachedCriteria(Book).build {
            eq 'isScientific', true
        }
        final count = criteria.count {
            eq 'author', a3
        }
        then:
        count == 2
    }

    void "detached criteria using countBy"() {
        given:
        Author a3 = Author.findByUsername("a3")
        when:
        final criteria = new DetachedCriteria(Book).build {
            eq 'isScientific', true
        }
        final count = criteria.countBy {
            it.author == a3
        }
        then:
        count[true] == 2
    }

    /** ================================================================================================================
     * EXPECT THESE TESTS TO FAIL
     *
     * They both generate the same SQL:
     * Hibernate: select count(*) as y0_ from book this_ where this_.author_id=? limit ?
     * ============================================================================================================== */

//    void "whereScientificOnly.countByAuthor"() {
//        given:
//        Author a3 = Author.findByUsername("a3")
//        expect:
//        Book.whereScientificOnly.countByAuthor(a3) == 2
//    }

//    void "detached criteria using countByAuthor DYNAMIC METHOD"() {
//        given:
//        Author a3 = Author.findByUsername("a3")
//        when:
//        final criteria = new DetachedCriteria(Book).build {
//            eq 'isScientific', true
//        }
//        final count = criteria.countByAuthor(a3)
//        then:
//        count == 2
//    }

}
