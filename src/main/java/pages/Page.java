package pages;

import core.Communication;

public interface Page {

	PageResult process(Communication communication) throws Exception;

}
