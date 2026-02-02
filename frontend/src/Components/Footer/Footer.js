// Footer.jsx
import React from 'react';
import { Link } from 'react-router-dom'; // 👈 Import Link
import './Footer.css';

const Footer = () => {
    const currentYear = new Date().getFullYear();

    return (
        <footer className="footer">
            <div className="footer-content">
                <p style={{ fontWeight: '700', fontSize: '1.2rem', marginBottom: '1rem' }}>SkillNetra</p>
                <p className="copyright">&copy; {currentYear} SkillNetra. All rights reserved.</p>
                <div className="footer-links">
                    {/* 🎯 Use Link for internal paths like /about */}
                    <Link to="/about">About Us</Link>

                    {/* If Privacy and Terms are also components in your app */}
                    <Link to="/privacy">Privacy Policy</Link>
                    <Link to="/terms">Terms of Service</Link>
                </div>
            </div>
        </footer>
    );
};

export default Footer;