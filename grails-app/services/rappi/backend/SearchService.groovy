package rappi.backend

import grails.transaction.Transactional

class SearchService {
	static transactional = false

    def queryObjects(object){
    	def c = Matrix.createCriteria()
		def results = c.list {
			ge("x",)
			ge("y",)
			ge("z",)
			le("x",)
			le("y",)
			le("z",)
		}
    }
}
