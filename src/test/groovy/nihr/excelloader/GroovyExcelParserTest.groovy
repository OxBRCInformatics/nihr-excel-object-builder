package nihr.excelloader

import spock.lang.Specification

/**
 * Created by rb on 11/03/2014.
 */
class GroovyExcelParserSpec extends Specification {
	def "Parse"() {
		when:
		def filename = 'src/test/resources/Critical Care CDS (version 4).xls'
		ExcelLoader parser = new ExcelLoader()
		def (headers, rows) = parser.parse(filename)
		println 'Headers'
		println '------------------'
		headers.each { header ->
			println header
		}
		println "\n"
		println 'Rows'
		println '------------------'
		rows.each { row ->
			println parser.toXml(headers, row)
		}

		then:
		true
	}

	def "GetRowData"() {

	}

	def "GetRowReference"() {

	}

	def "GetValue"() {

	}

	def "ToXml"() {

	}
}
