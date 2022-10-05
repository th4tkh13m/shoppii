import { createContext, useContext, useMemo } from 'react'
import { useNavigate } from 'react-router-dom'
import { useLocalStorage } from './useLocalStorage'

const AuthContext = createContext()

export const AuthProvider = ({ children }) => {
    const [role, setRole] = useLocalStorage('user', null)
    const navigate = useNavigate()

    const login = async data => {
        setRole(data)
        navigate('/dashboard/profile', { replace: true })
    }

    const logout = () => {
        setRole(null)
        navigate('/', { replace: true })
    }

    const value = useMemo(
        () => ({
            role,
            login,
            logout,
        }),
        [role],
    )

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export const useAuth = () => {
    return useContext(AuthContext)
}
