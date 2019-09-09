package com.selenium;

import java.util.Optional;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyTestWatcher extends Base implements TestWatcher
{
	public void testAborted(ExtensionContext extensionContext, Throwable throwable)
	{
		// do something
	}

	public void testDisabled(ExtensionContext extensionContext, Optional<String> optional)
	{
		// do something
	}

	public void testFailed(ExtensionContext extensionContext, Throwable throwable)
	{
		doScreenshot();
		System.out.println("'" + extensionContext.getDisplayName() + "' failed");
		driver.quit();
	}

	public void testSuccessful(ExtensionContext extensionContext)
	{
		System.out.println("'" + extensionContext.getDisplayName() + "' passed");
		driver.quit();
	}
}