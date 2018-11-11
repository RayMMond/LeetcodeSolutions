    public class Solution
    {
        //public int OpenLock(string[] deadEnds, string target)
        //{
        //    var step = 0;
        //    var deads = new HashSet<string>(deadEnds);
        //    var visited = new HashSet<string>();
        //    var queue = new Queue<string>();
        //    queue.Enqueue("0000");
        //    visited.Add("0000");

        //    while (queue.Count > 0)
        //    {
        //        var size = queue.Count;
        //        while (size > 0)
        //        {
        //            var s = queue.Dequeue();
        //            if (target == s)
        //            {
        //                return step;
        //            }

        //            if (deads.Contains(s))
        //            {
        //                size--;
        //                continue;
        //            }

        //            for (var i = 0; i < 4; i++)
        //            {
        //                var chars = s.ToCharArray();
        //                chars[i] = (char)(s[i] == '9' ? '0' : s[i] + 1);
        //                var s1 = new string(chars);
        //                if (!visited.Contains(s1) && !deads.Contains(s1))
        //                {
        //                    visited.Add(s1);
        //                    queue.Enqueue(s1);
        //                }
        //                chars = s.ToCharArray();
        //                chars[i] = (char)(s[i] == '0' ? '9' : s[i] - 1);
        //                s1 = new string(chars);
        //                if (!visited.Contains(s1) && !deads.Contains(s1))
        //                {
        //                    visited.Add(s1);
        //                    queue.Enqueue(s1);
        //                }
        //            }
        //            size--;
        //        }

        //        step++;
        //    }

        //    return -1;
        //}

        public int OpenLock(string[] deadends, string target)
        {
            var starts = new HashSet<string>();
            var ends = new HashSet<string>();
            starts.Add("0000");
            ends.Add(target);
            var deads = new HashSet<string>(deadends);
            var step = 0;
            while (starts.Count > 0 && ends.Count > 0)
            {
                if (starts.Count > ends.Count)
                {
                    (starts, ends) = (ends, starts);
                }

                var temp = new HashSet<string>();
                foreach (var s in starts)
                {
                    if (ends.Contains(s))
                    {
                        return step;
                    }
                    if (deads.Contains(s))
                    {
                        continue;
                    }
                    deads.Add(s);

                    for (var i = 0; i < 4; i++)
                    {
                        var s1 = s.Substring(0, i) + (char)(s[i] == '9' ? '0' : s[i] + 1) + s.Substring(i + 1);
                        if (!deads.Contains(s1))
                        {
                            temp.Add(s1);
                        }
                        var s2 = s.Substring(0, i) + (char)(s[i] == '0' ? '9' : s[i] - 1) + s.Substring(i + 1);
                        if (!deads.Contains(s2))
                        {
                            temp.Add(s2);
                        }
                    }

                }

                starts = temp;
                step++;
            }

            return -1;
        }
    }