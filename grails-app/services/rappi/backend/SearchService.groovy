package rappi.backend

import grails.transaction.Transactional

class SearchService {
	static transactional = false

    def queryObjects(object){
    	def c = Matrix.createCriteria()
		def results = c.list {
			ge("x", object.values[0] as Long)
			ge("y", object.values[1] as Long)
			ge("z", object.values[2] as Long)
			le("x", object.values[3] as Long)
			le("y", object.values[4] as Long)
			le("z", object.values[5] as Long)
		}
		return results
    }
}
