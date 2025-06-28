import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link, Navigate } from 'react-router-dom';
import { Container, Navbar, Nav, Button } from 'react-bootstrap';

import FeesPage from './pages/FeesPage';
import PaymentsPage from './pages/PaymentsPage';
import StudentFeesPage from './pages/StudentFeesPage';
import StudentsByGradePage from './pages/UserPages';
import LoginPage from './pages/LoginPage';
import AttendancePage from './pages/AttendancePage';

function App() {
  const [user, setUser] = useState(null);

  const handleLogout = () => {
    setUser(null);
  };

  // Navbar shown only if logged in
  const Navigation = () => (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/">School Fee Management</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          {user && (
            <>
              <Nav className="me-auto">
                {user.role.toLowerCase() === 'admin' && (
                  <>
                    <Nav.Link as={Link} to="/fees">Fees</Nav.Link>
                    <Nav.Link as={Link} to="/payments">Payments</Nav.Link>
                    <Nav.Link as={Link} to="/student-fees">Student Fees</Nav.Link>
                    <Nav.Link as={Link} to="/students-by-grade">Users</Nav.Link>
                    <Nav.Link as={Link} to="/attendance">Attendance</Nav.Link>
                  </>
                )}
                {user.role.toLowerCase() === 'student' && (
                  <Nav.Link as={Link} to="/student-info">My Info</Nav.Link>
                )}
              </Nav>
              <Button variant="outline-light" onClick={handleLogout}>Logout</Button>
            </>
          )}
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );

  return (
    <Router>
      <Navigation />

      <Container className="mt-4">
        <Routes>
          {!user && (
            <Route path="/*" element={<LoginPage onLoginSuccess={setUser} />} />
          )}

          {user && user.role.toLowerCase() === 'admin' && (
            <>
              <Route path="/" element={<Home />} />
              <Route path="/fees" element={<FeesPage />} />
              <Route path="/payments" element={<PaymentsPage />} />
              <Route path="/student-fees" element={<StudentFeesPage />} />
              <Route path="/students-by-grade" element={<StudentsByGradePage />} />
              <Route path="/attendance" element={<AttendancePage />} />
              {/* Redirect unknown routes to home */}
              <Route path="*" element={<Navigate to="/" />} />
            </>
          )}

          {user && user.role.toLowerCase() === 'student' && (
            <>
              <Route path="/" element={<StudentUnderDevelopment />} />
              <Route path="/student-info" element={<StudentUnderDevelopment />} />
              {/* Redirect all other routes to student page */}
              <Route path="*" element={<Navigate to="/" />} />
            </>
          )}
        </Routes>
      </Container>
    </Router>
  );
}

function Home() {
  return <h2 className="text-center">Welcome to the School Fee Management UI</h2>;
}

function StudentUnderDevelopment() {
  return (
    <div className="text-center">
      <h3>Student Portal</h3>
      <p>This page is under development for students.</p>
    </div>
  );
}

export default App;
