// Pages
// User
import * as paths from '../pages/user/index'

// Shop

// Admin
import Dashboard from '../pages/admin/Dashboard'
import Requests from '../pages/admin/Requests'

const {
    AlertToken,
    Profile,
    Home,
    Products,
    Cart,
    Checkout,
    ForgetPassword,
    LoginPage,
    Register,
    ViewShop,
    Product,
} = paths
// Routes
const publicRoutes = [
    {
        path: '/',
        component: Home,
    },
    {
        path: '/products',
        component: Products,
    },
    {
        path: '/profile',
        component: Profile,
    },
    {
        path: '/cart',
        component: Cart,
    },
    {
        path: '/checkout',
        component: Checkout,
    },
    {
        path: '/forget-password',
        component: ForgetPassword,
    },
    {
        path: '/login',
        component: LoginPage,
    },
    {
        path: '/register',
        component: Register,
    },
    {
        path: '/shop/:id',
        component: ViewShop,
    },
    {
        path: '/product/:id',
        component: Product,
    },
    {
        path: '/register-success',
        component: AlertToken,
    },
]

const privateRoutes = [
    {
        path: '/admin',
        component: Dashboard,
    },
    {
        path: '/admin/requests',
        component: Requests,
    },
]

export { publicRoutes, privateRoutes }
