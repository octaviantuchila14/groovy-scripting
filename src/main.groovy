@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )
import groovyx.net.http.RESTClient
import groovy.json.JsonSlurper


def client = new RESTClient( 'http://localhost:9000/api/resources/index/?resource=com.boa.jobmanager%3Ajob-management%3Aorigin%2Ffeature%2FWRAP-1-test&metrics=quality_gate_details&format=json' )

def response = client.get( path : 'msr/data/level' ) // ACME boomerang

def data = response.data[0].msr[0].data

def jsonSlurper = new JsonSlurper()
def jsonData = jsonSlurper.parseText(data)

def level = jsonData.level

assert level == "OK"