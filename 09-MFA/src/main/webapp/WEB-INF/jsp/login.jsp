<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.growl.css"/>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="https://cdn.auth0.com/js/lock/10.0/lock.min.js"></script>
    <script src="/js/jquery.growl.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <script type="text/javascript">
        $(function () {
            $.growl({title: "Welcome!", message: "Please log in"});
        });
        $(function () {
            var lock = new Auth0Lock('${clientId}', '${clientDomain}', {
                auth: {
                    redirectUrl: '${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}' + '/callback',
                    responseType: 'code',
                    params: {
                        state: '${state}',
                        // Learn about scopes: https://auth0.com/docs/scopes
                        scope: 'openid user_id name nickname email picture'
                    }
                }
            });
            // delay to allow welcome message..
            setTimeout(function () {
                lock.show();
            }, 1500);
        });
    </script>
</div>
</body>
</html>
