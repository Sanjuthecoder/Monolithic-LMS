import React from 'react';

export default function PrivacyPolicy() {
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
          Privacy Policy
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
          <Section title="1. Information We Collect">
            <p>We collect information that you provide directly to us, including:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>Name, email address, and contact information</li>
              <li>Account credentials and profile information</li>
              <li>Course enrollment and progress data</li>
              <li>Payment and billing information</li>
              <li>Communications and feedback</li>
            </ul>
          </Section>

          <Section title="2. How We Use Your Information">
            <p>We use the information we collect to:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>Provide, maintain, and improve our services</li>
              <li>Process transactions and send related information</li>
              <li>Send you technical notices and support messages</li>
              <li>Respond to your comments and questions</li>
              <li>Monitor and analyze trends and usage</li>
            </ul>
          </Section>

          <Section title="3. Information Sharing">
            <p>We do not sell your personal information. We may share your information only in the following circumstances:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>With your consent</li>
              <li>With service providers who assist our operations</li>
              <li>To comply with legal obligations</li>
              <li>To protect our rights and prevent fraud</li>
            </ul>
          </Section>

          <Section title="4. Data Security">
            <p>We implement appropriate technical and organizational measures to protect your personal information against unauthorized access, alteration, disclosure, or destruction. However, no method of transmission over the Internet is 100% secure.</p>
          </Section>

          <Section title="5. Your Rights">
            <p>You have the right to:</p>
            <ul style={{ marginLeft: '1.5rem', marginTop: '0.5rem' }}>
              <li>Access and update your personal information</li>
              <li>Request deletion of your data</li>
              <li>Opt-out of marketing communications</li>
              <li>Export your data</li>
            </ul>
          </Section>

          <Section title="6. Cookies">
            <p>We use cookies and similar tracking technologies to track activity on our service and hold certain information. You can instruct your browser to refuse all cookies or to indicate when a cookie is being sent.</p>
          </Section>

          <Section title="7. Contact Us" isLast>
            <p>If you have any questions about this Privacy Policy, please contact us at:</p>
            <p style={{ marginTop: '0.5rem', fontWeight: '500', color: '#667eea' }}>
              privacy@lms.example.com
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
