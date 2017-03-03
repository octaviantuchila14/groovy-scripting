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

Exception is: hudson.AbortException: Couldn't find any revision to build. Verify the repository and branch configuration for this job.


Stack trace: [hudson.plugins.git.GitSCM.determineRevisionToBuild(GitSCM.java:1002), hudson.plugins.git.GitSCM.checkout(GitSCM.java:1098), org.jenkinsci.plugins.workflow.steps.scm.SCMStep.checkout(SCMStep.java:109), org.jenkinsci.plugins.workflow.steps.scm.SCMStep$StepExecutionImpl.run(SCMStep.java:83), org.jenkinsci.plugins.workflow.steps.scm.SCMStep$StepExecutionImpl.run(SCMStep.java:73), org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution$1$1.call(AbstractSynchronousNonBlockingStepExecution.java:47), hudson.security.ACL.impersonate(ACL.java:260), org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution$1.run(AbstractSynchronousNonBlockingStepExecution.java:44), java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511), java.util.concurrent.FutureTask.run(FutureTask.java:266), java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142), java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617), java.lang.Thread.run(Thread.java:745)]

checkout([$class: 'GitSCM',
        branches: [[name: "origin/pr/${pullRequestId}/merge"]], doGenerateSubmoduleConfigurations: false,
        extensions: [], gitTool: 'jgit', submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: credentials,
                             name: 'origin', refspec: '+refs/pull-requests/*:refs/remotes/origin/pr/*',
                             url: repository]]])


sh("git config user.email my.email@google.com")
sh("git config user.name my-user-name")
sh("git tag ${BUILD_NUMBER}")
sh("git push origin --tags")

fatal: could not read Username for 'http://my-git-repo.com:8000': No such device or address
