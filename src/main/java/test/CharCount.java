package test;

public class CharCount {
	public static void main(String[] args) {
		String candidate = "{\"binding_method_score\":0.0,\"binding_method\":\"session.txn_history\",\"binding_event_GSID\":\"EGXtptSnSQLr\",\"binding_account_number\":1366334004606848107,\"binding_event_timestamp\":1402471304,\"binding_phone_id\":2390686}";
		int count = 0;
		for (int i =0; i<candidate.length();i++) {
			char c = candidate.charAt(i);
			if (c=='\"') {
				//ignore
			} else {
				count++;
			}
		}
		System.out.println(candidate.length());	
		System.out.println(count);	
	}
}