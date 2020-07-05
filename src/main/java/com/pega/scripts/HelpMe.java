

package com.pega.scripts;

public class HelpMe {

    public static final String ELMT_INFO = "window.helpMe = function helpMe(elmt, action){var info = '';var frames = getFrameStack(elmt);info = 'Frames stack: ' + frames + 'Top Document\\n';var id = elmt.id == '' ? null : elmt.id;var text = elmt.textContent == '' ? null : elmt.textContent;info = info + 'Id: ' + id + '\\n';info = info + 'Name: ' + elmt.getAttribute('name') + '\\n';info = info + 'Data Test Id: ' + elmt.getAttribute('data-test-id') + '\\n';info = info + 'Tag: ' + elmt.tagName + '\\n';info = info + 'TextContent: ' + text + '\\n-------------------------\\n';var design = frames == '' ? 'Since the inspected element is in Top Document, the page class should extend TopDocument. \\nEx: public interface <InterfaceName> extends TopDocument.' : 'Since the inspected element is in Iframe, the page class should extend Frame. \\nEx: public interface <InterfaceName> extends Frame.';design = design + ' \\nIn case of exisiting class review the parent class hierarchy.';info = info + 'Suggestions for best possible find options in priority order: \\n' + getAllFindOptions(elmt);info = info + '\\n---------------------\\nSuggestions for page class design/review: \\n' + design;if(action) {info = info + '\\n---------------------\\nSuggestions for framework methods to do given action in priority order: \\n' + getActionsList(action) + '\\nNote: <elmt> = findElement(<By>);'}return info;};function getActionsList(action) {var actions = '';action = action.toLowerCase();if(action == 'click') {actions = actions + '<elmt>.click(); \\nscriptExecutor.click(<elmt>); \\n<elmt>.doClickWithMouse();\\n'} else if(action == 'type' || action == 'sendkeys') {actions = actions + '<elmt>.sendKeys(<input>); \\nscriptExecutor.sendKeys(<elmt>, <input>); \\n'}return actions;}function getFrameStack(elmt) {var frames = '';var parent = elmt.ownerDocument.defaultView.frameElement;while(parent!=null){frames = frames + parent.id  + ' -> ';parent = parent.ownerDocument.defaultView.frameElement;}return frames;}function getAllFindOptions(elmt) {var findOptions = '';var text = elmt.textContent == '' ? null : elmt.textContent;var idStr = elmt.id && elmt.id !='' && elmt.id.match(\"^\\d+\") == null ? elmt.id : '';var nameStr = elmt.name && elmt.name !='' ? elmt.name : '';var linkStr = elmt.tagName == 'A' ? elmt.textContent : '';var dataTestId = elmt.getAttribute('data-test-id');var cssStr = elmt.getAttribute('data-test-id') != null ? elmt.tagName.toLowerCase() +'[data-test-id=\\'' + dataTestId + '\\']' : \"\";cssStr = elmt.getAttribute('title') != null && elmt.getAttribute('title') != '' && cssStr !=\"\" ? elmt.tagName.toLowerCase() + '[title=\\'' + elmt.getAttribute('title') + '\\']' : cssStr;cssStr = elmt.getAttribute('alt') != null && elmt.getAttribute('alt') != '' && cssStr !=\"\" ? elmt.tagName.toLowerCase() + '[alt=\\'' + elmt.getAttribute('alt') + '\\']' : cssStr;cssStr = elmt.getAttribute('placeholder') != null && elmt.getAttribute('placeholder') != '' && cssStr !=\"\" ? elmt.tagName.toLowerCase() + '[placeholder=\\'' + elmt.getAttribute('placeholder') + '\\']' : cssStr;var xpathStr = getXpathExpr(elmt);findOptions = idStr!='' ? getMethodName(elmt) + '(By.id(\"'+idStr+'\"))' + '\\n': findOptions;findOptions = nameStr!='' ? findOptions + getMethodName(elmt) + '(By.name(\"'+nameStr+'\"))' + '\\n' : findOptions;findOptions = linkStr!='' ? findOptions + getMethodName(elmt) + '(By.linkText(\"'+linkStr+'\"))' + '\\n' : findOptions;findOptions = cssStr!='' ? findOptions + getMethodName(elmt) + '(By.cssSelector(\"'+cssStr+'\"))' + '\\n' : findOptions;findOptions = xpathStr!='' ? findOptions + getMethodName(elmt) + '(By.xpath('+xpathStr+'))' : findOptions;findOptions = findOptions=='' ? 'Not Found' : findOptions.substring(0, findOptions.length - 1);return findOptions;}function getXpathExpr(elmt) {var text = elmt.textContent == '' ? null : elmt.textContent;var xpathStr = elmt.tagName.toLowerCase()=='span' && elmt.className.indexOf('menu-item-title') != -1 ? 'XPathUtil.getMenuItemXPath(\"'+elmt.textContent+'\")' : '';xpathStr = elmt.tagName.toLowerCase()=='button' && elmt.className.indexOf('pzbutton') != -1 ? 'XPathUtil.getButtonpzButtonXpath(\"'+elmt.textContent+'\")' : xpathStr;xpathStr = text != null && xpathStr==\"\" ? '\"//' + elmt.tagName.toLowerCase() + '[text()=\\'' + text + '\\']\"' : xpathStr;return xpathStr;}function getMethodName(elmt) {var methodName='findElement';if(elmt.tagName.toLowerCase()=='select') {methodName='findSelectBox';} else if(elmt.tagName.toLowerCase()=='input' && elmt.className.indexOf('autocomplete_input') != -1) {methodName='findAutoComplete'}return methodName;}";
}
