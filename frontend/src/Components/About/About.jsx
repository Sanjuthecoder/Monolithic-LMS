import React from 'react';

export default function About() {
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
          About SkillNetra
        </h1>
        <p style={{ fontSize: '1.2rem', opacity: '0.95', maxWidth: '700px', margin: '0 auto' }}>
          Empowering learners and educators with cutting-edge technology
        </p>
      </div>

      {/* Content Section */}
      <div style={{
        maxWidth: '1000px',
        margin: '0 auto',
        padding: '3rem 2rem'
      }}>
        {/* Mission */}
        <div style={{ marginBottom: '3rem' }}>
          <h2 style={{
            fontSize: '2rem',
            fontWeight: '700',
            color: '#1f2937',
            marginBottom: '1rem',
            background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
            WebkitBackgroundClip: 'text',
            WebkitTextFillColor: 'transparent'
          }}>
            Our Mission
          </h2>
          <p style={{ fontSize: '1.1rem', lineHeight: '1.8', color: '#4b5563' }}>
            We believe that quality education should be accessible to everyone, everywhere. Our Learning Management System is designed to bridge the gap between traditional classroom learning and modern digital education, providing tools that enhance the learning experience for both students and instructors.
          </p>
        </div>

        {/* Features Grid */}
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))',
          gap: '2rem',
          marginBottom: '3rem'
        }}>
          {[
            { title: 'Interactive Learning', desc: 'Engage with course materials through videos, quizzes, and interactive assignments.' },
            { title: 'Progress Tracking', desc: 'Monitor your learning journey with detailed analytics and achievement badges.' },
            { title: 'Collaborative Tools', desc: 'Connect with peers and instructors through discussion forums and group projects.' },
            { title: 'Mobile Friendly', desc: 'Access your courses anytime, anywhere on any device.' }
          ].map((feature, index) => (
            <div key={index} style={{
              background: 'white',
              padding: '2rem',
              borderRadius: '12px',
              boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
              transition: 'transform 0.3s ease',
            }}>
              <h3 style={{
                fontSize: '1.3rem',
                fontWeight: '600',
                color: '#667eea',
                marginBottom: '0.75rem'
              }}>
                {feature.title}
              </h3>
              <p style={{ color: '#6b7280', lineHeight: '1.6' }}>
                {feature.desc}
              </p>
            </div>
          ))}
        </div>

        {/* Team Section */}
        <div style={{
          background: 'white',
          padding: '2.5rem',
          borderRadius: '12px',
          boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)'
        }}>
          <h2 style={{
            fontSize: '2rem',
            fontWeight: '700',
            color: '#1f2937',
            marginBottom: '1rem',
            textAlign: 'center'
          }}>
            Our Values
          </h2>
          <div style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))',
            gap: '1.5rem',
            marginTop: '2rem'
          }}>
            {['Excellence', 'Innovation', 'Accessibility', 'Community'].map((value, index) => (
              <div key={index} style={{ textAlign: 'center', padding: '1rem' }}>
                <div style={{
                  width: '60px',
                  height: '60px',
                  background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
                  borderRadius: '50%',
                  margin: '0 auto 1rem',
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'center',
                  color: 'white',
                  fontSize: '1.5rem',
                  fontWeight: '700'
                }}>
                  {value[0]}
                </div>
                <h4 style={{ fontSize: '1.1rem', fontWeight: '600', color: '#1f2937' }}>
                  {value}
                </h4>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}