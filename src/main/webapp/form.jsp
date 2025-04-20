<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Job Application Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Software Developer | Full-Stack Developers Java </h2>
        <p class="text-center">Chennai, India | Posted on 09/24/2024</p>

        <!-- Job Application Form -->
        <form action="/submitApplication" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="resume">Upload Resume (PDF/DOCX)</label>
                <input type="file" class="form-control" id="resume" name="resume" accept=".pdf,.docx,.doc">
            </div>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required>
            </div>
            <div class="form-group">
                <label for="mobile">Mobile</label>
                <input type="tel" class="form-control" id="mobile" name="mobile" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="degree">Highest Degree</label>
                <select class="form-control" id="degree" name="degree" required>
                    <option value="">Select your degree</option>
                    <option value="B.Tech">B.Tech</option>
                    <option value="M.Tech">M.Tech</option>
                </select>
            </div>
            <div class="form-group">
                <label for="collegeName">College Name</label>
                <input type="text" class="form-control" id="collegeName" name="collegeName" required>
            </div>
            <div class="form-group">
                <label for="experience">Total Experience (Years)</label>
                <input type="number" class="form-control" id="experience" name="experience" required>
            </div>
            <div class="form-group">
                <label for="relevantExperience">Relevant Experience (Years)</label>
                <input type="number" class="form-control" id="relevantExperience" name="relevantExperience" required>
            </div>
            <div class="form-group">
                <label for="noticePeriod">Notice Period</label>
                <select class="form-control" id="noticePeriod" name="noticePeriod" required>
                    <option value="">Select</option>
                    <option value="Immediate">Immediate</option>
                    <option value="30 Days">30 Days</option>
                    <option value="60 Days">60 Days</option>
                </select>
            </div>
            <div class="form-group">
                <label for="skills">Technical Skill Set</label>
                <input type="text" class="form-control" id="skills" name="skills" required>
            </div>

            <!-- Submit Buttons -->
            <button type="submit" class="btn btn-primary">Easy Apply</button>
            <button type="submit" class="btn btn-info">Apply with \</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
