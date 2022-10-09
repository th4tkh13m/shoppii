import Visibility from '@mui/icons-material/Visibility'
import VisibilityOff from '@mui/icons-material/VisibilityOff'
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
import AppRegistrationIcon from '@mui/icons-material/AppRegistration'
import { useState } from 'react'
import { handleChange } from '../.././utils/handleForm'

function RegisterForm() {
    const [showPassword, setShowPassword] = useState(false)
    const [showRePassword, setShowRePassword] = useState(false)
    const [user, setUser] = useState({})
    const handleSubmit = event => {
        event.preventDefault()
        console.log(user)
    }
    return (
        <>
            <Container component="div" maxWidth="xs">
                <Box className="d-flex flex-column align-items-center">
                    <Avatar
                        sx={{
                            m: 1,
                            bgcolor: 'primary.main',
                            width: '50px',
                            height: '50px',
                        }}
                    >
                        <AppRegistrationIcon sx={{ fontSize: '24px' }} />
                    </Avatar>
                    <Typography component="h1" variant="h4">
                        Đăng Ký
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
                            label="Nhập email hoặc số điện thoại"
                            name="email"
                            autoComplete="email"
                            autoFocus
                            InputProps={{
                                style: { fontSize: '1.5rem' },
                                onChange: e => handleChange(e, setUser),
                                inputProps: {
                                    pattern: process.env.REACT_APP_REGEX_AUTH,
                                    title: 'Vui lòng nhập email hoặc số điện thoại',
                                },
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
                            label="Mật khẩu"
                            type={showPassword ? 'text' : 'password'}
                            id="password"
                            autoComplete="current-password"
                            InputProps={{
                                style: { fontSize: '1.5rem' },
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label="toggle password visibility"
                                            onMouseDown={e => {
                                                setShowPassword(true)
                                            }}
                                            onMouseUp={e => {
                                                setShowPassword(false)
                                            }}
                                            edge="end"
                                        >
                                            {showPassword ? (
                                                <VisibilityOff
                                                    sx={{
                                                        fontSize: '2.5rem',
                                                    }}
                                                />
                                            ) : (
                                                <Visibility
                                                    sx={{
                                                        fontSize: '2.5rem',
                                                    }}
                                                />
                                            )}
                                        </IconButton>
                                    </InputAdornment>
                                ),
                                onChange: e => handleChange(e, setUser),
                            }}
                            InputLabelProps={{
                                style: { fontSize: '1.5rem' },
                            }}
                        />
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            name="repassword"
                            label="Nhập lại mật khẩu"
                            type={showRePassword ? 'text' : 'password'}
                            id="repassword"
                            autoComplete="current-password"
                            InputProps={{
                                style: { fontSize: '1.5rem' },
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton
                                            aria-label="toggle password visibility"
                                            onMouseDown={e => {
                                                setShowRePassword(true)
                                            }}
                                            onMouseUp={e => {
                                                setShowRePassword(false)
                                            }}
                                            edge="end"
                                        >
                                            {showRePassword ? (
                                                <VisibilityOff
                                                    sx={{
                                                        fontSize: '2.5rem',
                                                    }}
                                                />
                                            ) : (
                                                <Visibility
                                                    sx={{
                                                        fontSize: '2.5rem',
                                                    }}
                                                />
                                            )}
                                        </IconButton>
                                    </InputAdornment>
                                ),
                                onChange: e => handleChange(e, setUser),
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
                            Đăng Ký
                        </Button>
                    </Box>
                </Box>
            </Container>
        </>
    )
}

export default RegisterForm
