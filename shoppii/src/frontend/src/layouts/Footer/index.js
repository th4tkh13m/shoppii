/* eslint-disable jsx-a11y/anchor-is-valid */
import clsx from 'clsx'
import styles from './index.module.css'
import {
    FacebookRounded,
    Instagram,
    Twitter,
    GitHub,
} from '@mui/icons-material/'
import { Link } from 'react-router-dom'
function Footer() {
    return (
        <body className={clsx(styles.body)}>
            <footer>
                <div className="container">
                    <div className={clsx('row', 'justify-content-center')}>
                        <div className={clsx('col-md-12', 'text-center')}>
                            <h2>Shoppii.com</h2>
                            <ul className={clsx(styles.socialMedia)}>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                            textDecoration: 'none',
                                            fontSize: '2rem',
                                        }}
                                    >
                                        Home
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                            textDecoration: 'none',
                                            fontSize: '2rem',
                                        }}
                                    >
                                        AboutUs
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                            textDecoration: 'none',
                                            fontSize: '2rem',
                                        }}
                                    >
                                        Contact
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                            textDecoration: 'none',
                                            fontSize: '2rem',
                                        }}
                                    >
                                        Blog
                                    </Link>
                                </li>
                            </ul>
                            <ul className={clsx(styles.socialMedia)}>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                        }}
                                    >
                                        {<Twitter />}
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                        }}
                                    >
                                        {<FacebookRounded />}
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                        }}
                                    >
                                        {<Instagram />}
                                    </Link>
                                </li>
                                <li>
                                    <Link
                                        to={'#'}
                                        style={{
                                            color: 'rgb(156 163 175 / 3)',
                                        }}
                                    >
                                        {<GitHub />}
                                    </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        </body>
    )
}

export default Footer
