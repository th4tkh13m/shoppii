import { createContext, useContext, useMemo } from 'react'
import { useNavigate } from 'react-router-dom'
import { useLocalStorage } from './useLocalStorage'

const AuthContext = createContext()

export const AuthProvider = ({ children }) => {
    const [role, setRole] = useLocalStorage('role', 'admin')
    const navigate = useNavigate()

    const login = async data => {
        // fetch data here to get role

        const ROLE = 'admin'
        setRole(ROLE)
        // navigate('/dashboard/profile', { replace: true })
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
