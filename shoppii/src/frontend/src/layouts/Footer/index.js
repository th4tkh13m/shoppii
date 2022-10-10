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
                            <h2 className={styles.footerHeading}>
                                Shoppii.com
                            </h2>
                            <ul className={clsx(styles.socialMedia)}>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        Home
                                    </Link>
                                </li>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        AboutUs
                                    </Link>
                                </li>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        Contact
                                    </Link>
                                </li>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        Blog
                                    </Link>
                                </li>
                            </ul>
                            <ul className={clsx(styles.socialMedia)}>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        {<Twitter />}
                                    </Link>
                                </li>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        {<FacebookRounded />}
                                    </Link>
                                </li>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
                                        {<Instagram />}
                                    </Link>
                                </li>
                                <li>
                                    <Link to={'#'} className={styles.linkStyle}>
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
