<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Screening Form</title>
     <link rel="icon" type="image/x-icon" href="https://res.cloudinary.com/dq7pblvqg/image/upload/v1730571973/PathAhead/1_djhr1h.png">
    <!-- Bootstrap CSS -->
    <style>
        /* Ensures the image is exactly 300x40px */
        .header-logo {
            width: 100%;
            height: auto;
            object-fit: cover; /* Maintains aspect ratio if image dimensions differ */
        }
        

        
    </style>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="container mt-5">
<!-- style="background: rgb(38,192,233);
background: linear-gradient(194deg, rgba(38,192,233,1) 0%, rgba(204,41,221,1) 39%);
" -->
<!-- #623AA6 -->
    <!-- h2 class="text-center mb-4">Screening Form</h2>-->
        <img src="https://res.cloudinary.com/dq7pblvqg/image/upload/v1730573033/PathAhead/SCREENING_FORM_CROP4_1_b3jnc0.png" 
        alt="Logo" class="header-logo">
    <form action="submitForm" method="post">
        <!-- Name -->
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <!-- Phone Number -->
        <div class="form-group">
            <label for="phone_num">Phone Number:</label>
            <input type="text" class="form-control" id="phone_num" name="phone_num" required>
        </div>

        <!-- Email -->
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <!-- Work Experience -->
        <div class="form-group">
            <label for="work_experience">Work Experience:</label>
            <textarea class="form-control" id="work_experience" name="work_experience" rows="4" required></textarea>
        </div>

        <!-- Academic Background -->
        <div class="form-group">
            <label for="academic_background">Academic Background:</label>
            <textarea class="form-control" id="academic_background" name="academic_background" rows="4" required></textarea>
        </div>

        <!-- Certifications -->
        <div class="form-group">
            <label for="certifications">Certifications:</label>
            <textarea class="form-control" id="certifications" name="certifications" rows="4"></textarea>
        </div>

        <!-- Preferred Job Role -->
        <div class="form-group">
            <label for="preferred_job_role">Preferred Job Role:</label>
            <select class="form-control" id="preferred_job_role" name="preferred_job_role" required>
                <option value="Software Engineer">Software Engineer</option>
                <option value="Data Analyst">Data Analyst</option>
                <option value="Product Manager">Product Manager</option>
                <option value="HR Intern">HR Intern</option>
                <option value="SDE Intern">SDE Intern</option>
                <option value="Front-End Developer">Front-End Developer</option>
                <option value="Back-End Developer">Back-End Developer</option>
                <option value="PHP Developer">PHP Developer</option>
                <option value="VLSI Engineer">VLSI Engineer</option>
                <option value="CAD Designer">CAD Designer</option>
                <option value="Electrical Engineer">Electrical Engineer</option>
                <option value="Chip Designer">Chip Designer</option>
                <option value="Intern">Intern</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <!-- Resume -->
        <div class="form-group">
            <label for="resume">Resume:</label>
            <textarea class="form-control" id="resume" name="resume" rows="4" required></textarea>
        </div>

        <!-- Cover Letter -->
        <div class="form-group">
            <label for="cover_letter">Cover Letter:</label>
            <textarea class="form-control" id="cover_letter" name="cover_letter" rows="4" required></textarea>
        </div>

        <!-- Submit Button -->
        <button type="submit" class="btn btn-primary btn-block">Submit</button>
    </form>
    
    

    <!-- Bootstrap JS and dependencies (Optional) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
