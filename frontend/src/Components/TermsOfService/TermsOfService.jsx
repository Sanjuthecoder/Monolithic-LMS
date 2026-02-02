import React from 'react';

export default function TermsOfService() {
  return (
    <div style={{
      minHeight: 'calc(100vh - 70px)',
      background: 'linear-gradient(to bottom, #f9fafb 0%, #ffffff 100%)'
    }}>
      {/* Hero Section */}
      <div style={{
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        padding: '4rem 2rem',
        textAlign: 'center',
        color: 'white'
      }}>
        <h1 style={{
          fontSize: '3rem',
          fontWeight: '700',
          marginBottom: '1rem',
          letterSpacing: '-1px'
        }}>
          Terms of Service
        </h1>
        <p style={{ fontSize: '1.1rem', opacity: '0.95' }}>
          Last updated: January 23, 2026
        </p>
      </div>

      {/* Content Section */}
      <div style={{
        maxWidth: '900px',
        margin: '0 auto',
        padding: '3rem 2rem'
      }}>
        <div style={{
          background: 'white',
          padding: '3rem',
          borderRadius: '12px',
          boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
          lineHeight: '1.8',
          color: '#4b5563'
        }}>
          <Section title="1. Acceptance of Terms">
            <p>By accessing and using this Learning Management System, you accept and agree to be bound by the terms and provision of this agreement. If you do not agree to abide by these terms, please do not use this service.</p>
          </Section>

          <Section title="2. User Accounts">
            <p>To access certain features of the platform, you must create an account. You agree to:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>Provide accurate and complete information</li>
              <li>Maintain the security of your password</li>
              <li>Notify us immediately of any unauthorized use</li>
              <li>Be responsible for all activities under your account</li>
            </ul>
          </Section>

          <Section title="3. User Conduct">
            <p>You agree not to:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>Use the service for any illegal purpose</li>
              <li>Violate any laws in your jurisdiction</li>
              <li>Share inappropriate or offensive content</li>
              <li>Attempt to gain unauthorized access to the system</li>
              <li>Interfere with or disrupt the service</li>
              <li>Impersonate any person or entity</li>
            </ul>
          </Section>

          <Section title="4. Intellectual Property">
            <p>The service and its original content, features, and functionality are owned by the LMS platform and are protected by international copyright, trademark, and other intellectual property laws.</p>
          </Section>

          <Section title="5. Course Content">
            <p>Course materials are provided for educational purposes only. You may not:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>Redistribute or resell course content</li>
              <li>Copy or download content except as permitted</li>
              <li>Use content for commercial purposes</li>
            </ul>
          </Section>

          <Section title="6. Payments and Refunds">
            <p>All payments are processed securely. Refund policies vary by course and are clearly stated at the time of purchase. Generally, refunds are available within 14 days of purchase if less than 20% of the course has been completed.</p>
          </Section>

          <Section title="7. Termination">
            <p>We reserve the right to terminate or suspend your account and access to the service immediately, without prior notice, for conduct that we believe violates these Terms of Service or is harmful to other users, us, or third parties.</p>
          </Section>

          <Section title="8. Limitation of Liability">
            <p>The service is provided "as is" without warranties of any kind. We shall not be liable for any indirect, incidental, special, consequential, or punitive damages resulting from your use of the service.</p>
          </Section>

          <Section title="9. Changes to Terms">
            <p>We reserve the right to modify these terms at any time. We will notify users of any material changes. Your continued use of the service after such modifications constitutes acceptance of the updated terms.</p>
          </Section>

          <Section title="10. Contact Information" isLast>
            <p>For questions about these Terms of Service, please contact us at:</p>
            <p style={{ marginTop: '0.5rem', fontWeight: '500', color: '#667eea' }}>
              legal@lms.example.com
            </p>
          </Section>
        </div>
      </div>
    </div>
  );
}

function Section({ title, children, isLast }) {
  return (
    <div style={{ marginBottom: isLast ? '0' : '2.5rem' }}>
      <h2 style={{
        fontSize: '1.5rem',
        fontWeight: '700',
        color: '#1f2937',
        marginBottom: '1rem'
      }}>
        {title}
      </h2>
      <div style={{ fontSize: '1rem' }}>
        {children}
      </div>
    </div>
  );
}
