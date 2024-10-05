<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta name="keywords" content="Home HTML CSS Bootstrap Customer Survey"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
        <title>Murach's Java Servlets and JSP</title>

        <style>
            h1 {
                font-size: 300%;
                font-weight: bold;
                color: white;
            }
            h2, label {
                font-size: 120%;
                font-weight: bold;
                color: royalblue;
                margin-top: 10px;
            }
            textarea {
                font-family: Consolas, Arial, serif;
            }
            button {
                margin-top: 10px;
                width: 200px;
                float: right;
            }
            .sql-result {
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-md-8 offset-md-2">
                    <c:if test="${sqlCommand == null}">
                        <c:set var="sqlCommand" value="SELECT * FROM UserTest;" />
                    </c:if>

                    <div class="mt-2 p-5 bg-primary text-white rounded">
                        <h1>The SQL Gateway</h1>
                        <p>Enter an SQL statement and click the Execute button.</p>
                    </div>

                    <div>
                        <br>
                        <form action="sql-gateway" method="post">
                            <div class="">
                                <label for="sql-command">SQL statement:</label>
                                <textarea class="form-control" name="sqlCommand" id="sql-command" rows="3">
                                    ${sqlCommand.trim()}
                                </textarea>
                            </div>
                            <button type="submit" class="btn btn-outline-primary">Execute Command</button>
                        </form>
                    </div>

                    <div class="sql-result">
                        <h2>SQL result:</h2>
                        ${sqlResult}
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>