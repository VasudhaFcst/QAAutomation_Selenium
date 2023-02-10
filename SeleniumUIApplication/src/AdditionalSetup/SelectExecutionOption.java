package AdditionalSetup;

public class SelectExecutionOption {
	static ExecutionPart selectOption;

	public static ExecutionPart getSelectOption() {
		return selectOption;
	}

	public static void setSelectOption(ExecutionPart selectOption) {
		SelectExecutionOption.selectOption = selectOption;
	}
}
