package rappi.backend

import grails.converters.JSON
import groovy.json.StringEscapeUtils;

class CubeController {

	def cubeService

    def calculate() { 
    	println request.JSON.data
    	render cubeService.calculate(request.JSON.data) as JSON
    }
}
