import React, { useState, useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './ProfileMenu.css';

export default function ProfileMenu() {
    const [dropdownOpen, setDropdownOpen] = useState(false);
    const navigate = useNavigate();
    const dropdownRef = useRef(null);

    const userName = localStorage.getItem('userName') || 'User';
    const firstLetter = userName.charAt(0).toUpperCase();

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('userName');
        navigate('/login');
    };

    // Close dropdown when clicking outside
    useEffect(() => {
        function handleClickOutside(event) {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setDropdownOpen(false);
            }
        }
        document.addEventListener('mousedown', handleClickOutside);
        return () => document.removeEventListener('mousedown', handleClickOutside);
    }, []);

    return (
        <div className="profile-menu" ref={dropdownRef}>
            <div
                className="profile-avatar"
                onClick={() => setDropdownOpen(!dropdownOpen)}
            >
                {firstLetter}
            </div>
            {dropdownOpen && (
                <div className="profile-dropdown">
                    <div
                        className="profile-info"
                        onClick={() => {
                            const role = localStorage.getItem('role');
                            if (role === 'ADMIN') {
                                navigate('/admin');
                            } else if (role === 'STUDENT') {
                                navigate('/student');
                            }
                            setDropdownOpen(false);
                        }}
                        style={{ cursor: 'pointer' }}
                    >
                        <p className="profile-name">{userName}</p>
                        <p className="profile-role">{localStorage.getItem('role')}</p>
                    </div>
                    <button
                        className="home-btn"
                        onClick={() => {
                            const role = localStorage.getItem('role');
                            if (role === 'ADMIN') {
                                navigate('/admin');
                            } else if (role === 'STUDENT') {
                                navigate('/student');
                            }
                            setDropdownOpen(false);
                        }}
                    >
                        Home
                    </button>
                    <button className="logout-btn" onClick={handleLogout}>
                        Logout
                    </button>
                </div>
            )}
        </div>
    );
}
