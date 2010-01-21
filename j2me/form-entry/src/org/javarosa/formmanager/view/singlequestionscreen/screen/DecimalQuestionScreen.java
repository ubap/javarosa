/*
 * Copyright (C) 2009 JavaRosa
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.javarosa.formmanager.view.singlequestionscreen.screen;

import javax.microedition.lcdui.TextField;

import org.javarosa.core.model.data.DecimalData;
import org.javarosa.core.model.data.IAnswerData;
import org.javarosa.form.api.FormEntryPrompt;

import de.enough.polish.ui.Style;

public class DecimalQuestionScreen extends SingleQuestionScreen {
	protected TextField tf;

	public DecimalQuestionScreen(FormEntryPrompt prompt, String groupName, Style style) {
		super(prompt,groupName,style);
	}

	public void createView() {

		// #style textBox
		tf = new TextField("", "", 200, TextField.DECIMAL);

		if (prompt.isRequired())
			tf.setLabel("*" + prompt.getLongText());
		else
			tf.setLabel(prompt.getLongText());

		IAnswerData answerData = prompt.getAnswerValue();
		if ((answerData != null) && (answerData instanceof DecimalData))
			tf.setString(((DecimalData) answerData).getDisplayText());

		this.append(tf);
		this.addNavigationWidgets();
		if (prompt.getHelpText() != null) {
			setHint(prompt.getHelpText());
		}
	}

	public IAnswerData getWidgetValue() {
		String s = tf.getString();

		if (s == null || s.equals(""))
			return null;

		double d = -999999999;
		try {
			d = Double.parseDouble(s);

		} catch (NumberFormatException nfe) {
			System.err.println("Non-numeric data in numeric entry field!");
		}
		return new DecimalData(d);

	}

}