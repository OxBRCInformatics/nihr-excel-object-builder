package nihr.excelloader.nihr.excelloader

import nihr.excelloader.ExcelLoader
import spock.lang.Specification

/**
 * Created by rb on 11/03/2014.
 */
class SchemaGeneratorSpec extends Specification {

	def "normalise"(){
		when:
		def filename = 'src/test/resources/Critical Care CDS (version 4).xls'
		ExcelLoader parser = new ExcelLoader()
		def (headers, rows) = parser.parse(filename)

		SchemaGenerator g = new SchemaGenerator()

		def normalised = g.normalise(headers, rows)

		normalised.each {
			println it
		}
		then:
		true
	}
}
