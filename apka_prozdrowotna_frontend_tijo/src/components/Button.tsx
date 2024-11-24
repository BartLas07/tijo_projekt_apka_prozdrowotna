import React, { CSSProperties } from 'react';

interface ButtonProps {
  text: string;
  onClick?: () => void;
  customStyles?: CSSProperties;
}

const Button = ({ text, onClick, customStyles }: ButtonProps) => {
  const buttonStyle = {
    width: '100%',
    padding: '15px',
    fontSize: '18px',
    color: '#fff',
    backgroundColor: '#7ad557',
    border: 'none',
    borderRadius: '8px',
    fontWeight: 'bold',
    cursor: 'pointer',
    transition: 'background-color 0.3s ease',
    ...customStyles,
  };

  return (
    <button
      style={buttonStyle}
      onClick={onClick}
      onMouseOver={(e) => e.currentTarget.style.backgroundColor = '#6ab346'}
      onMouseOut={(e) => e.currentTarget.style.backgroundColor = customStyles?.backgroundColor || '#7ad557'}
    >
      {text}
    </button>
  );
};

export default Button;
