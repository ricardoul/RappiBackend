package rappi.backend

class CubeTests extends GroovyTestCase {

	def cubeService
    static transactional = false
    void testMyTransactionalServiceMethod() {
    	assert cubeService.calculate() == "hola2"    

    }
}