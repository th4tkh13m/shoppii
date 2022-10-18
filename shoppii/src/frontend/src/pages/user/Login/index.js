import './index.css'
import Image from '../../../assets/images/bd2e86e454da37f2e6c9a128c8e9a2b8.png'
import logoGoogle from '../../../assets/images/2991148.png'
import {
    InputAdornment,
    IconButton,
    Container,
    Typography,
    Avatar,
    Button,
    TextField,
    Box,
} from '@mui/material'
import LockOutlinedIcon from '@mui/icons-material/LockOutlined'
import Visibility from '@mui/icons-material/Visibility'
import VisibilityOff from '@mui/icons-material/VisibilityOff'
import { Row, Col } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import { useState } from 'react'

function LoginPage() {
    const handleSubmit = event => {}
    const [showPassword, setShowPassword] = useState(false)

    return (
        <Row className="wrapper">
            <Col md={6} className="left">
                <img
                    src={Image}
                    alt=""
                    style={{ height: '100%', width: '100%' }}
                />
            </Col>
            <Col md={6} className="right d-flex justify-content-center">
                <div className="d-flex flex-column">
                    <Container component="div" maxWidth="xs">
                        <Box
                            sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'center',
                            }}
                        >
                            <Avatar
                                sx={{
                                    m: 1,
                                    bgcolor: 'primary.main',
                                    width: '50px',
                                    height: '50px',
                                }}
                            >
                                <LockOutlinedIcon sx={{ fontSize: '24px' }} />
                            </Avatar>
                            <Typography component="h1" variant="h4">
                                Đăng Nhập
                            </Typography>
                            <Box
                                component="form"
                                onSubmit={handleSubmit}
                                sx={{ mt: 1 }}
                            >
                                <TextField
                                    margin="normal"
                                    required
                                    fullWidth
                                    id="email"
                                    label="Email Or Phone Number"
                                    name="email"
                                    autoComplete="email"
                                    autoFocus
                                    InputProps={{
                                        style: { fontSize: '1.5rem' },
                                    }}
                                    InputLabelProps={{
                                        style: { fontSize: '1.5rem' },
                                    }}
                                />
                                <TextField
                                    margin="normal"
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="current-password"
                                    InputProps={{
                                        style: { fontSize: '1.5rem' },
                                        endAdornment: (
                                            <InputAdornment position="end">
                                                <IconButton
                                                    aria-label="toggle password visibility"
                                                    onClick={() =>
                                                        setShowPassword(
                                                            !showPassword,
                                                        )
                                                    }
                                                    onMouseDown={e =>
                                                        e.preventDefault()
                                                    }
                                                    edge="end"
                                                >
                                                    {showPassword ? (
                                                        <VisibilityOff
                                                            sx={{
                                                                fontSize:
                                                                    '2.5rem',
                                                            }}
                                                        />
                                                    ) : (
                                                        <Visibility
                                                            sx={{
                                                                fontSize:
                                                                    '2.5rem',
                                                            }}
                                                        />
                                                    )}
                                                </IconButton>
                                            </InputAdornment>
                                        ),
                                    }}
                                    InputLabelProps={{
                                        style: { fontSize: '1.5rem' },
                                    }}
                                />
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    sx={{ mt: 3, mb: 2, fontSize: '1.4rem' }}
                                >
                                    Đăng Nhập
                                </Button>
                            </Box>
                        </Box>
                    </Container>
                    <Box
                        component="div"
                        sx={{
                            display: 'flex',
                            flexDirection: 'column',
                            // alignItems: 'center',
                            padding: '0 24px',
                        }}
                    >
                        <Typography
                            component="h1"
                            variant="h5"
                            sx={{
                                margin: '0 auto',
                                fontWeight: 'bold',
                            }}
                        >
                            HOẶC
                        </Typography>
                        <Box
                            component="button"
                            sx={{
                                width: '396px',
                                height: '55px',
                                display: 'flex',
                                justifyContent: 'center',
                                alignItems: 'center',
                                boxShadow: 'var(--box-shadow-main-2)',
                                borderRadius: '8px',
                                padding: '8px 0',
                                outline: 'none',
                                border: 'none',
                                marginTop: '6px',
                                transition: 'var(--transition-normal)',
                                '&:hover': {
                                    transform: 'scale(.95)',
                                    border: 'var(--main-blue) 1px solid',
                                    // color: 'var(--main-white)',
                                },
                            }}
                        >
                            <img
                                src={logoGoogle}
                                alt=""
                                style={{
                                    width: '35px',
                                }}
                            />
                            <Typography
                                component="h2"
                                variant="h5"
                                sx={{
                                    marginLeft: '24px',
                                }}
                            >
                                Đăng nhập bằng tài khoản Google
                            </Typography>
                        </Box>
                        <Box
                            component="div"
                            className="d-flex justify-content-between align-items-center"
                            sx={{
                                marginTop: '12px',
                            }}
                        >
                            <Link to="/forget-password">
                                <Typography
                                    component="h5"
                                    sx={{ fontSize: '1.5rem' }}
                                >
                                    Quên mật khẩu?
                                </Typography>
                            </Link>
                            <Link to="/register">
                                <Typography
                                    component="h5"
                                    sx={{ fontSize: '1.5rem' }}
                                >
                                    Đăng ký
                                </Typography>
                            </Link>
                        </Box>
                    </Box>
                </div>
            </Col>
        </Row>
    )
}

export default LoginPage
