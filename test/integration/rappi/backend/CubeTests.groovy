package rappi.backend

class CubeTests extends GroovyTestCase {

	def cubeService
    static transactional = false
    void testMyTransactionalServiceMethod() {

    	def text = '''2
4 5
UPDATE 2 2 2 4
QUERY 1 1 1 3 3 3
UPDATE 1 1 1 23
QUERY 2 2 2 4 4 4
QUERY 1 1 1 3 3 3
2 7
UPDATE 2 2 2 1
UPDATE 2 2 2 1
UPDATE 2 2 2 1
UPDATE 2 2 2 1
QUERY 1 1 1 1 1 1
QUERY 1 1 1 2 2 2
QUERY 2 2 2 2 2 2'''
    	cubeService.calculate(text)    

    }
}