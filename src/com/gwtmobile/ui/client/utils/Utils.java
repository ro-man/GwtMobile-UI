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

package com.gwtmobile.ui.client.utils;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Utils {

	public static native void Console(String msg) /*-{
//		if ($wnd.console) {
//			$wnd.console.log(msg);
//		}
//		else 
		{
			var log = $doc.getElementById('log');
			if (log) {
				log.innerHTML = msg + '<br/>' + log.innerHTML;
			}
			else {
              if ($wnd.console) {
                  $wnd.console.log(msg);
              }
			}
		}		
	}-*/;

	public static native JavaScriptObject addEventListener(Element ele, String event, boolean capture, EventListener listener) /*-{
		var callBack = function(e) {
			listener.@com.google.gwt.user.client.EventListener::onBrowserEvent(Lcom/google/gwt/user/client/Event;)(e);			
		};
		ele.addEventListener(event, callBack, capture);
		return callBack;
	}-*/;
	
	public static native void addEventListenerOnce(Element ele, String event, boolean capture, EventListener listener) /*-{
		var callBack = function(e) {
			ele.removeEventListener(event, callBack, capture);
			listener.@com.google.gwt.user.client.EventListener::onBrowserEvent(Lcom/google/gwt/user/client/Event;)(e);			
		};
		ele.addEventListener(event, callBack, capture);
	}-*/;

    public static native void removeEventListener(Element ele, String event, boolean capture, JavaScriptObject callBack) /*-{
        ele.removeEventListener(event, callBack, capture);
    }-*/;

	
    private static Element htmlNode = DOM.createElement("DIV");
    public static String unescapeHTML(String html) {
        htmlNode.setInnerHTML(html);
        return htmlNode.getInnerText(); 
    }
    
    //The url loaded by this method can be intercepted by 
    //WebViewClient.shouldOverrideUrlLoading
    public static void loadUrl(String url) {
		Anchor a = new Anchor("", url);
		RootLayoutPanel.get().add(a);
		NativeEvent event = Document.get().createClickEvent(1, 1, 1, 1, 1, false, false, false, false);
		a.getElement().dispatchEvent(event);
		RootLayoutPanel.get().remove(a);
	}

    public static boolean isHtmlFormControl(com.google.gwt.dom.client.Element ele) {
    	if (ele == null) {
    		return false;
    	}
    	String FromControls = "BUTTON INPUT SELECT TEXTAREA";
    	String nodeName = ele.getNodeName().toUpperCase();
    	return FromControls.contains(nodeName) 
    		|| isHtmlFormControl(ele.getParentElement());
    }
    
    public native static Element getActiveElement() /*-{
    	return $doc.activeElement;
    }-*/;
}
