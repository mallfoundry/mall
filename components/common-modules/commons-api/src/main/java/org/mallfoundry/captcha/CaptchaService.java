/*
 * Copyright (C) 2019-2020 the original author or authors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.mallfoundry.captcha;

/**
 * @author Tang Zhi
 * @since 1.0
 */
public interface CaptchaService {

    Captcha createCaptcha(CaptchaType type);

    Captcha getCaptcha(String token);

    Captcha generateCaptcha(Captcha captcha) throws CaptchaException;

    /**
     * 在使用之前，请先获得 Captcha 对象进行比较请求参数。如：手机号、邮箱地址等。
     */
    boolean checkCaptcha(String token, String code);
}
