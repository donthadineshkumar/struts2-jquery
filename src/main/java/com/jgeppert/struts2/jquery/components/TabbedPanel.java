/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.jgeppert.struts2.jquery.components;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.apache.struts2.views.annotations.StrutsTagSkipInheritance;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * <!-- START SNIPPET: javadoc -->
 * <p>
 * The tabbedpanel widget is primarily an AJAX component, where each tab can
 * either be local content or remote content.
 * </p>
 * <p>
 * If the useSelectedTabCookie attribute is set to true, the id of the selected
 * tab is saved in a cookie on activation. When coming back to this view, the
 * cookie is read and the tab will be activated again, unless an actual value
 * for the selectedTab attribute is specified.
 * </p>
 * <!-- END SNIPPET: javadoc -->
 * 
 * <p>
 * <b>Examples</b>
 * </p>
 * 
 * <!-- START SNIPPET: example1 --> &lt;sj:tabbedpanel id="mytabs"
 * animate="true" collapsible="true" useSelectedTabCookie="true"&gt; &lt;sj:tab
 * id="tab1" target="tone" label="Tab One"/&gt; &lt;sj:tab id="tab2"
 * target="ttwo" label="Tab Two"/&gt; &lt;sj:tab id="tab3" target="tthree"
 * label="Tab Three"/&gt; &lt;div id="tone"&gt; Test 1 &lt;/div&gt; &lt;div
 * id="ttwo"&gt; Test 2 &lt;/div&gt; &lt;div id="tthree"&gt; Test 3 &lt;/div&gt;
 * &lt;/sj:tabbedpanel&gt; <!-- END SNIPPET: example1 -->
 * 
 * <!-- START SNIPPET: example2 --> &lt;s:url id="remoteurl1"
 * action="myremoteactionone"/&gt; &lt;s:url id="remoteurl2"
 * action="myremoteactiontwo"/&gt; &lt;s:url id="remoteurl3"
 * action="myremoteactionthree"/&gt; &lt;sj:tabbedpanel id="mytabs2"
 * selectedTab="1"&gt; &lt;sj:tab id="tab1" href="%{remoteurl1}"
 * label="Tab One"/&gt; &lt;sj:tab id="tab2" href="%{remoteurl2}"
 * label="Tab Two"/&gt; &lt;sj:tab id="tab3" href="%{remoteurl3}"
 * label="Tab Three"/&gt; &lt;/sj:tabbedpanel&gt; <!-- END SNIPPET: example2 -->
 */
@StrutsTag(name = "tabbedpanel", tldTagClass = "com.jgeppert.struts2.jquery.views.jsp.ui.TabbedPanelTag", description = "Render a tabbedPanel widget.")
public class TabbedPanel extends AbstractTopicsBean {
  public static final String            JQUERYACTION   = "tabbedpanel";
  public static final String            TEMPLATE       = "tabbedpanel";
  public static final String            TEMPLATE_CLOSE = "tabbedpanel-close";
  final private static String           COMPONENT_NAME = TabbedPanel.class.getName();
  private final static transient Random RANDOM         = new Random();

  protected String                      selectedTab;
  protected String                      useSelectedTabCookie;
  protected String                      openOnMouseover;
  protected String                      collapsible;
  protected String                      animate;
  protected String                      spinner;
  protected String                      cache;
  protected String                      disabledTabs;

  protected String                      onAddTopics;
  protected String                      onRemoveTopics;

  public TabbedPanel(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
    super(stack, request, response);
  }

  public void evaluateExtraParams()
  {
    super.evaluateExtraParams();

    addParameter("jqueryaction", JQUERYACTION);

    if (selectedTab != null) addParameter("selectedTab", findValue(selectedTab, Integer.class));
    if (useSelectedTabCookie != null) addParameter("useSelectedTabCookie", findValue(this.useSelectedTabCookie, Boolean.class));
    if (this.openOnMouseover != null) addParameter("openOnMouseover", findValue(this.openOnMouseover, Boolean.class));
    if (this.collapsible != null) addParameter("collapsible", findValue(this.collapsible, Boolean.class));
    if (this.animate != null) addParameter("animate", findValue(this.animate, Boolean.class));
    if (this.spinner != null) addParameter("spinner", findString(this.spinner));
    if (this.cache != null) addParameter("cache", findValue(this.cache, Boolean.class));
    if (this.disabledTabs != null) addParameter("disabledTabs", findString(this.disabledTabs));
    if (this.onAddTopics != null) addParameter("onAddTopics", findString(this.onAddTopics));
    if (this.onRemoveTopics != null) addParameter("onRemoveTopics", findString(this.onRemoveTopics));

    if ((this.id == null || this.id.length() == 0))
    {
      // resolves Math.abs(Integer.MIN_VALUE) issue reported by FindBugs
      // http://findbugs.sourceforge.net/bugDescriptions.html#RV_ABSOLUTE_VALUE_OF_RANDOM_INT
      int nextInt = RANDOM.nextInt();
      nextInt = nextInt == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(nextInt);
      this.id = "tabbedpanel_" + String.valueOf(nextInt);
      addParameter("id", this.id);
    }
  }

  @Override
  @StrutsTagSkipInheritance
  public void setTheme(String theme)
  {
    super.setTheme(theme);
  }

  @Override
  public String getTheme()
  {
    return "jquery";
  }

  public String getDefaultOpenTemplate()
  {
    return TEMPLATE;
  }

  protected String getDefaultTemplate()
  {
    return TEMPLATE_CLOSE;
  }

  public String getComponentName()
  {
    return COMPONENT_NAME;
  }

  @StrutsTagAttribute(description = "The id to assign to the component.", required = true)
  public void setId(String id)
  {
    // This is required to override tld generation attributes to required=true
    super.setId(id);
  }

  @StrutsTagAttribute(description = "Number of tab that will be selected by default. e.g. 0 for the first tab or 1 for the second tab.", type = "Integer", defaultValue = "0")
  public void setSelectedTab(String selectedTab)
  {
    this.selectedTab = selectedTab;
  }

  @StrutsTagAttribute(description = "Open Tabs by mouseover event", defaultValue = "false", type = "Boolean")
  public void setOpenOnMouseover(String openOnMouseover)
  {
    this.openOnMouseover = openOnMouseover;
  }

  @StrutsTagAttribute(description = "Set to true to allow an already selected tab to become unselected again upon reselection", defaultValue = "false", type = "Boolean")
  public void setCollapsible(String collapsible)
  {
    this.collapsible = collapsible;
  }

  @StrutsTagAttribute(description = "Enable animations for hiding and showing tab panels", defaultValue = "false", type = "Boolean")
  public void setAnimate(String animate)
  {
    this.animate = animate;
  }

  @StrutsTagAttribute(description = "Store the latest selected tab in a cookie. The cookie is then used to determine the initially selected tab if the selectedTab option is not defined.", defaultValue = "false", type = "Boolean")
  public void setUseSelectedTabCookie(String useSelectedTabCookie)
  {
    this.useSelectedTabCookie = useSelectedTabCookie;
  }

  @StrutsTagAttribute(description = "The HTML content of this string is shown in a tab title while remote content is loading. Pass in empty string to deactivate that behavior.")
  public void setSpinner(String spinner)
  {
    this.spinner = spinner;
  }

  @StrutsTagAttribute(description = "Whether or not to cache remote tabs content, e.g. load only once or with every click. Cached content is being lazy loaded, e.g once and only once for the first click.", defaultValue = "false", type = "Boolean")
  public void setCache(String cache)
  {
    this.cache = cache;
  }

  @StrutsTagAttribute(description = "An array containing the position of the tabs (zero-based index) that should be disabled on initialization. e.g. [1, 2]")
  public void setDisabledTabs(String disabledTabs)
  {
    this.disabledTabs = disabledTabs;
  }

  @StrutsTagAttribute(description = "A comma delimited list of topics that published when a tab is added", type = "String", defaultValue = "")
  public void setOnAddTopics(String onAddTopics)
  {
    this.onAddTopics = onAddTopics;
  }

  @StrutsTagAttribute(description = "A comma delimited list of topics that published when a tab is removed", type = "String", defaultValue = "")
  public void setOnRemoveTopics(String onRemoveTopics)
  {
    this.onRemoveTopics = onRemoveTopics;
  }
}