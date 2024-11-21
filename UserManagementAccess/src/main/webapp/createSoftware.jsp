<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Software</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Create New Software</h2>
        <form action="software" method="post">
            <div class="mb-3">
                <label for="softwareName" class="form-label">Software Name</label>
                <input type="text" class="form-control" id="softwareName" name="name" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Access Levels</label><br>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="accessLevels" value="Read">
                    <label class="form-check-label" for="read">Read</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="accessLevels" value="Write">
                    <label class="form-check-label" for="write">Write</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" name="accessLevels" value="Admin">
                    <label class="form-check-label" for="admin">Admin</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary w-100">Create Software</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
