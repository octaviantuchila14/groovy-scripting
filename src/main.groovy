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



I have a Jenkins script with this call:

    mvn "-e -X release:prepare"

This fails because git is not found: `/bin/sh: git: command not found`(it is calling git internally).

In the script, I am calling git like this: `sh("{env.git} status")`, which works.

Because of that, I am trying to pass the git environment variable to Maven:

    mvn "-Dgit=${env.git} -e -X release:prepare"

However, this again fails with `/bin/sh: git: command not found`.

So it looks like the address of Git hasn't been properly sent to Maven. Any idea why?

Pass Git location as a parameter to Maven


  <scm>
    <url>http://my-repo.com:7990/projects/repos/my-project</url>
    <connection>scm:git:ssh://git@my-repo.com:7999/wrap/my-project.git</connection>
    <developerConnection>scm:git:ssh://git@my-repo.com:7999/wrap/my-project.git</developerConnection>
    <tag>job-management-1.1</tag>
  </scm>


        <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-release-plugin</artifactId>
                <version>2.5.3</version>
        </plugin>


[ERROR] Failed to execute goal org.apache.maven.plugins:maven-release-plugin:2.3.2:prepare (default-cli) on project my-project: Unable to tag SCM
[ERROR] Provider message:
[ERROR] The git-push command failed.
[ERROR] Command output:
[ERROR] Permission denied (publickey).
[ERROR] fatal: Could not read from remote repository.
[ERROR] 
[ERROR] Please make sure you have the correct access rights
[ERROR] and the repository exists.
        
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-release-plugin:2.3.2:prepare (default-cli) on project my-project: Unable to tag SCM
[ERROR] Provider message:
[ERROR] The git-tag command failed.
[ERROR] Command output:
[ERROR] fatal: tag 'my-project-1.4-SNAPSHOT' already exists
[ERROR] -> [Help 1]

        
        
                        mvn release:prepare

          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-release-plugin</artifactId>
            <version>2.5.3</version>
            <configuration>
              <tagNameFormat>${env.BUILD_NUMBER}</tagNameFormat>
            </configuration>
          </plugin>

mvn release:prepare -tag=${env.BUILD_NUMBER}

Unable to tag SCM
[ERROR] Provider message:
[ERROR] The git-tag command failed.
[ERROR] Command output:
[ERROR] fatal: tag 'my-project-1.3' already exists
