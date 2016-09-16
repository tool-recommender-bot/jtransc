package com.jtransc.gradle.tasks

import com.jtransc.ConfigLibraries
import com.jtransc.error.invalidOp
import org.gradle.api.tasks.TaskAction

open class JTranscGradleRunTask() : AbstractJTranscGradleTask() {
	companion object {
		val name: String = JTranscGradleRunTask::class.java.name
	}

	@Suppress("unused")
	@TaskAction open fun task() {
		logger.info("buildAndRunRedirecting $name : $target")
		//println("buildAndRunRedirecting $name : $target")
		val build = prepare()
		val result = build.buildAndRunRedirecting()
		afterBuild(build)
		val process = result.process
		if (!process.success) {
			invalidOp("Process exited with code ${process.exitValue}")
		}
	}
}
