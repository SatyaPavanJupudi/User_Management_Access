<%@ page import="java.util.*" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Access</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Request Software Access</h2>
        <form action="request" method="post">
            <div class="mb-3">
                <label for="software" class="form-label">Software Name</label>
                <select class="form-select" id="software" name="software" required>
                    <option selected disabled>Choose software</option>
                    <%
                        List<String> softwareNames = (List<String>) request.getAttribute("softwareNames");
                        if (softwareNames != null && !softwareNames.isEmpty()) {
                            for (String software : softwareNames) {
                    %>
                        <option value="<%= software %>"><%= software %></option>
                    <%
                            }
                        } else {
                    %>
                        <option>No software available</option>
                    <%
                        }
                    %>
                </select>
            </div>
            <div class="mb-3">
                <label for="accessType" class="form-label">Access Type</label>
                <select class="form-select" id="accessType" name="accessType" required>
                    <option selected disabled>Choose access type</option>
                    <option value="Read">Read</option>
                    <option value="Write">Write</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="reason" class="form-label">Reason for Request</label>
                <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
            </div>
            <button type="submit" class="btn btn-primary w-100">Submit Request</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
