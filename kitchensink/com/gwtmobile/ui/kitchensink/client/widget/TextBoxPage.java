/*
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
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

package com.gwtmobile.ui.kitchensink.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.utils.Utils;
import com.gwtmobile.ui.client.widgets.TextBox;

public class TextBoxPage extends Page{

	@UiField TextBox textbox;
	
	private static TextBoxPageUiBinder uiBinder = GWT
			.create(TextBoxPageUiBinder.class);

	interface TextBoxPageUiBinder extends UiBinder<Widget, TextBoxPage> {
	}

	public TextBoxPage() {
		initWidget(uiBinder.createAndBindUi(this));	
	}
	
	@Override
	public void goBack(Object returnValue) {
		Element focus = Utils.getActiveElement();
		focus.blur();
		super.goBack(returnValue);
	}
}
