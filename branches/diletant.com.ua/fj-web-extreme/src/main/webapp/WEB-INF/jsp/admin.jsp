<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/xhtml+xml; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/xhtml+xml; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/classes/css/style.css"/>"/>
<title>Admin</title>
</head>
<body>
    <!-- ###### Header ###### -->

    <div id="header">
      <a href='/' class='logo'><img src='<c:url value="/images/all/title.gif"/>' /></a>
      <a href='http://www.donor.org.ua/index.php?module=help' class='top-banner' target="_blank" title='Украинская Открытая Ассоциация Организаций, Групп и Лиц, работающих с детьми, страдающими онкозаболеваниями'><img src='<c:url value="banner/donor_2.gif"/>' /></a>
    </div>

    <div class="fj-menu-ext">
    <div class="fj-menu-int"><img src='picts/index.gif' class='mnu_pict'/><a class='mnu_lnk' href='<c:url value="index.php"/>'>Список тем</a></div>
    </div>

    <!-- ###### Side Boxes ###### -->

    <div class="sideBox LHS">
      <div>This Page</div>
      <a href="#introduction">Introduction</a>
      <a href="#compliance">Compliance</a>

      <a href="#noTables">No Tables</a>
      <a href="#browserSpec">Browser Spec</a>
      <a href="#conclusion">Conclusion</a>
    </div>

    <div class="sideBox RHS">
      <div>Left or Right</div>

      <span>
        These boxes can also appear on the right. Just replace the style
        <strong>LHS</strong> with <strong>RHS</strong>, and voil&agrave;!
      </span>
    </div>

    <div class="sideBox LHS">
      <div>Adding Boxes</div>

      <span>Adding menus and hint boxes is simple!</span>
    </div>

    <!-- ###### Body Text ###### -->

    <div id="bodyText">
      <h1 id="introduction">Introduction</h1>
      <p>Welcome to Purple Haze: a simple, standards-compliant webpage design. The HTML of
         this design is very simple and easy to follow since it isn't cluttered with obsolete
         formatting tags. Most measurements are font size-relative, thus allowing the viewer
         to zoom the text size without compromising the layout.</p>

      <a class="topOfPage" href="#top" title="Go to top of page">top</a>

      <h1 id="compliance">Standards Compliant</h1>
      <p>The markup of Purple Haze is 100% compliant with XHTML 1.0 Strict. Additionally,
         it uses <acronym title="Cascading Style Sheets">CSS</acronym> extensively, thus
         separating formatting information from HTML content. Changing color schemes is simple:
         all you need to do is change four color values in the stylesheet. So if you hate the
         color purple... no problem!</p>

      <p>Due to its compliance, both Internet Explorer and Mozilla-based browsers render
         Purple Haze in standards-compliance mode. This ensures that users of both paradigms
         see (almost!) exactly the same web page in terms of formatting and layout.</p>

      <p>The markup is also compliant with the fairly new XHTML 1.1 standard. Just add an
         <acronym title="eXtensible Markup Language">XML</acronym> declaration to the beginning
         of the HTML document and change the <span class="smallCaps">doctype</span>.</p>

      <a class="topOfPage" href="#top" title="Go to top of page">top</a>

      <h1 id="noTables">No Tables</h1>

      <q>Tables are for displaying tabular data... not for use as design elements!</q>
 
      <p>Tables are for displaying tabular data... not for use as design elements! Purple
         Haze uses no tables for layout purposes. This is true for the menu on the left, the text
         boxes, the header, and the footer.</p>

      <p>Instead, Purple Haze makes use of the &lt;div&gt; and &lt;span&gt; tags as well as CSS.
         Use the <code>position: absolute;</code> attribute-value pair in your stylesheets to align
         text into columns.</p>

      <a class="topOfPage" href="#top" title="Go to top of page">top</a>

      <h1 id="browserSpec">Browser Specificity</h1>
      <p>Unfortuately, Internet Explorer (version 6.0 inclusive) is not completely standards
         compliant. This means that some styles may be rendered differently between Internet
         Explorer and Mozilla-based browsers.</p>

      <p>As a workaround, browser-specific styles are employed in Purple Haze. Most websites
         achieve this by using JavaScript to match separate stylesheets to specific browsers.
         Purple Haze, in contrast, doesn't use any JavaScript nor does it use separate
         stylesheets.</p>

      <p>Instead, two versions of the troublesome styles are written: one using
         <acronym title="Cascading Stylesheets version 1">CSS1</acronym> and the other using
         <acronym title="Cascading Stylesheets version 2">CSS2</acronym>. Both browser types
         understand the CSS1 version, but only the Mozilla-based browsers understand the CSS2
         version. The outcome: browser-specific styles!</p>

      <a class="topOfPage" href="#top" title="Go to top of page">top</a>

      <h1 id="conclusion">Conclusion</h1>
      <p>I hope you enjoy Purple Haze as much as I've enjoyed coding it. This design, like so
         many others, once again demonstrates the power of CSS and standards-compliant XHTML.
         You don't need to sacrifice style to be compliant!</p>
    </div>
    
    <!-- ###### Footer ###### -->

    <div><div id="footer">  <!-- NB: outer <div> required for correct rendering in IE -->

      <a class="footerImg" href="http://validator.w3.org/check/referer">
        Valid XHTML 1.0 Strict</a>
      <a class="footerImg" href="http://jigsaw.w3.org/css-validator/validator">
        Valid CSS</a>

      <div>
        <strong>Author: </strong>
        <a class="footerCol2" href="mailto:haran{insert@here}wiredcity.com.au"
           title="Email author">haran</a>

      </div>

      <div>
        <strong>URI: </strong>
        <span class="footerCol2">http://www.oswd.org/design/xxx/purplehaze/index.html</span>
      </div>

      <div>
        <strong>Modified: </strong>

        <span class="footerCol2">2002-12-15 17:34 +0800</span>
      </div>
    </div></div></body>
</html>