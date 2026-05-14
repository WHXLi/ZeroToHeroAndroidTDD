import org.gradle.api.GradleException
import java.io.ByteArrayOutputStream

fun runCommand(command: String): String {
    val output = ByteArrayOutputStream()

    project.exec {
        commandLine = command.split(" ")
        standardOutput = output
    }

    return output.toString().trim()
}

fun getGitBranch(): String {
    return runCommand("git rev-parse --abbrev-ref HEAD")
}

fun extractVersionFromBranch(branch: String): Int {
    val regex = Regex("""feature/0*(\d+)""")
    val match = regex.matchEntire(branch)
        ?: throw GradleException(
            "Ветка должна быть вида feature/000505110, текущая: $branch"
        )

    return match.groupValues[1].toInt()
}

fun getCommitCountInBranch(): Int {
    val baseBranch = "develop"

    val count = runCommand(
        "git rev-list --count --first-parent $baseBranch..HEAD"
    ).toInt()

    return if (count == 0) 1 else count
}

data class VersionData(
    val major: Int,
    val minor: Int,
    val versionName: String,
    val versionCode: Int
)

fun getVersionData(): VersionData {
    val branch = getGitBranch()

    if (branch == "HEAD") {
        throw GradleException("Detached HEAD не поддерживается")
    }

    val major = extractVersionFromBranch(branch)
    val minor = getCommitCountInBranch()

    return VersionData(
        major = major,
        minor = minor,
        versionName = "$major.$minor",
        versionCode = "${major}${minor.toString().padStart(3, '0')}".toInt()
    )
}