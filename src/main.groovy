ssh -p 7999 -i /c/Users/my-username/.ssh/id_rsa git@my-repo.com whoami

my-username

ssh -T git@code.bankofamerica.com

This is a private computer system with access restricted to
those with proper authorization.  If you are not specifically
authorized to access data on this system, disconnect now.
All information and communications on this system are
subject to review, monitoring, and recording at any time
without notice or permission.  Unauthorized use or access
may be subject to prosecution or disciplinary action.


Password:
Password:
Password:
Permission denied (publickey,gssapi-keyex,gssapi-with-mic,keyboard-interactive).

    
    
    

Password:
Password:
Password:
Permission denied (publickey,gssapi-keyex,gssapi-with-mic,keyboard-interactive).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.




<scm>
    <connection>scm:git:ssh://git@my-repo.com:7999/my-project.git</connection>
    <developerConnection>scm:git:ssh://git@my-repo.com:7999/wrap/my-project.git</developerConnection>
    <tag>HEAD</tag>
</scm>



Cloning a git repo using SSH

I am trying to clone a git repository using SSH. Therefore, I created an ssh key pair on my local machine and I added the public key on my git repo(which is Bitbucket server).

Then, as I saw here, I tried cloning like this:
https://kuttler.eu/en/post/git-clone-ssh-could-not-resolve-hostname/
        
git clone ssh://my_username@my-repository.com:7999/my_project.git

git clone ssh://git@my-repository.com:7999/my_project.git

These options didn't work however:

Permission denied (publickey).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
        


The only bit of progress I has was by trying this:
        
        
git clone my_username@my-repository.com:7990/wrap/my_project.git

This asked for my password 3 times and then failed. I suspect that this doesn't use SSH though, 
        because I think SSH should not ask for a password.

Password:
Password:
Password:
Permission denied (publickey,gssapi-keyex,gssapi-with-mic,keyboard-interactive).
fatal: Could not read from remote repository.
        
        
        So how can I clone a git repo using SSH?




        
        
http://jenkins-ci.361315.n4.nabble.com/Build-fauiler-while-plublishing-the-plugin-td4849155.html

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














  <scm>
    <connection>scm:git:ssh://myrepository.com:7999/my_project.git</connection>
    <developerConnection>scm:git:ssh://git@code.myrepository.com:7999/my_project.git</developerConnection>
    <tag>HEAD</tag>
  </scm>





[ERROR] Failed to execute goal org.apache.maven.plugins:maven-release-plugin:2.3.2:prepare (default-cli) on project my_project: Unable to tag SCM
[ERROR] Provider message:
[ERROR] The git-push command failed.
[ERROR] Command output:
[ERROR] Permission denied (publickey).
[ERROR] fatal: Could not read from remote repository.
[ERROR]
[ERROR] Please make sure you have the correct access rights
[ERROR] and the repository exists.
[ERROR] -> [Help 1]
org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal org.apache.maven.plugins:maven-release-plugin:2.3.2:prepare (default-cli) on project my_project: Unable to tag SCM
Provider message:
The git-push command failed.
Command output:
Permission denied (publickey).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.

        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:212)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:153)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:145)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:116)
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:80)
        at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build(SingleThreadedBuilder.java:51)
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:128)
        at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:307)
        at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:193)
        at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:106)
        at org.apache.maven.cli.MavenCli.execute(MavenCli.java:863)
        at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:288)
        at org.apache.maven.cli.MavenCli.main(MavenCli.java:199)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:289)
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:229)
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:415)
        at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:356)
Caused by: org.apache.maven.plugin.MojoFailureException: Unable to tag SCM
Provider message:
The git-push command failed.
Command output:
Permission denied (publickey).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.

        at org.apache.maven.plugins.release.PrepareReleaseMojo.prepareRelease(PrepareReleaseMojo.java:299)
        at org.apache.maven.plugins.release.PrepareReleaseMojo.execute(PrepareReleaseMojo.java:247)
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:134)
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:207)
        ... 20 more
Caused by: org.apache.maven.shared.release.scm.ReleaseScmCommandException: Unable to tag SCM
Provider message:
The git-push command failed.
Command output:
Permission denied (publickey).
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.

        at org.apache.maven.shared.release.phase.ScmTagPhase.execute(ScmTagPhase.java:136)
        at org.apache.maven.shared.release.DefaultReleaseManager.prepare(DefaultReleaseManager.java:234)
        at org.apache.maven.shared.release.DefaultReleaseManager.prepare(DefaultReleaseManager.java:169)
        at org.apache.maven.shared.release.DefaultReleaseManager.prepare(DefaultReleaseManager.java:146)
        at org.apache.maven.shared.release.DefaultReleaseManager.prepare(DefaultReleaseManager.java:107)
        at org.apache.maven.plugins.release.PrepareReleaseMojo.prepareRelease(PrepareReleaseMojo.java:291)
        ... 23 more

