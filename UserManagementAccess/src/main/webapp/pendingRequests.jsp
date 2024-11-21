<%@ page import="java.util.*" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Access Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Pending Access Requests</h2>
        <table class="table table-bordered table-striped mt-4">
            <thead class="thead-dark">
                <tr>
                    <th class="table-dark">Employee Name</th>
                    <th class="table-dark">Software Name</th>
                    <th class="table-dark">Access Type</th>
                    <th class="table-dark">Reason</th>
                    <th class="table-dark">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Map<String, Object>> requests = (List<Map<String, Object>>) request.getAttribute("requests");
                    if (requests != null && !requests.isEmpty()) {
                        for (Map<String, Object> req : requests) {
                %>
                <tr>
                    <td><%= req.get("employeeName") %></td>
                    <td><%= req.get("softwareName") %></td>
                    <td><%= req.get("accessType") %></td>
                    <td><%= req.get("reason") %></td>
                    <td>
                        <form action="approve" method="post" style="display:inline;">
                            <input type="hidden" name="requestId" value="<%= req.get("id") %>">
                            <button type="submit" name="action" value="approve" class="btn btn-success btn-sm">Approve</button>
                        </form>
                        <form action="approve" method="post" style="display:inline;">
                            <input type="hidden" name="requestId" value="<%= req.get("id") %>">
                            <button type="submit" name="action" value="reject" class="btn btn-danger btn-sm">Reject</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">No pending requests found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
