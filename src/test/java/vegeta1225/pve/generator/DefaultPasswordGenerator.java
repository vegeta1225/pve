package vegeta1225.pve.generator;

public class DefaultPasswordGenerator implements PasswordGenerator {

	public String getShorterPassword() {
		return "a";
	}

	public String getLongerPassword() {
		return "1234567890123";
	}

	public String getNoDigitPassword() {
		return "abcdef";
	}

	public String getNoLowerCaseLetterPassword() {
		return "ABCD1";
	}

	public String getFollowedRepeatedSequencePassword() {
		return "abc123abc123";
	}

	public String getFollowedRepeatedSubsequencePassword() {
		return "abc12123";
	}

	public String getNormalPassword() {
		return "abc123";
	}
}
