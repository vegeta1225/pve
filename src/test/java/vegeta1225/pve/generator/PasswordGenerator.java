package vegeta1225.pve.generator;

public interface PasswordGenerator {
	public String getShorterPassword();
	public String getLongerPassword();
	public String getNoDigitPassword();
	public String getNoLowerCaseLetterPassword();
	public String getFollowedRepeatedSequencePassword();
	public String getFollowedRepeatedSubsequencePassword();
	public String getNormalPassword();
}
